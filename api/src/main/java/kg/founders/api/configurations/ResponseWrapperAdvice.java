package kg.founders.api.configurations;

import kg.founders.api.configurations.properties.ResponseWrappingProperties;
import kg.founders.common.annotations.DisableResponseMessageResponse;
import kg.founders.common.annotations.ResponseMessageController;
import kg.founders.common.models.wrapper.ResponseWrapperModel;
import kg.founders.common.models.wrapper.WrapperResponseMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.annotation.Annotation;

@Slf4j
@RequiredArgsConstructor
@ControllerAdvice(annotations = ResponseMessageController.class)
@ConditionalOnProperty(
        prefix = "app.response-wrapping",
        name = "enabled",
        havingValue = "true"
)
public class ResponseWrapperAdvice implements ResponseBodyAdvice<Object> {
    private final ResponseWrappingProperties wrappingProperties;

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        for (Annotation parameterAnnotation : methodParameter.getMethodAnnotations()) {
            if (parameterAnnotation.annotationType() == DisableResponseMessageResponse.class) {
                return false;
            }
        }

        return true;
    }

    @Override
    @SneakyThrows
    @SuppressWarnings("rawtypes")
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter,
                                  MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        Class<? extends ResponseWrapperModel> wrapperClass = extractWrapperClass(methodParameter);

        Object responseResult;

        if (wrapperClass != null) {
            ResponseWrapperModel wrapper = wrapperClass.getDeclaredConstructor().newInstance();
            wrapper.setResult(body);
            wrapper.setResultCode(wrapper.extractResultCodeType());
            responseResult = wrapper;
        } else {
            responseResult = body;
        }

        log.debug("RETURNED RESPONSE from {}: {}", serverHttpRequest.getURI(), responseResult);

        return responseResult;
    }

    protected Class<? extends ResponseWrapperModel> extractWrapperClass(MethodParameter methodParameter) throws ClassNotFoundException {
        Class<? extends ResponseWrapperModel> annotatedWrapperClass = null;
        for (Annotation annotation : methodParameter.getContainingClass().getAnnotations()) {
            if (annotation.annotationType() == ResponseMessageController.class) {
                annotatedWrapperClass = ((ResponseMessageController) annotation).wrapperClass();
                break;
            }
        }

        Class<? extends ResponseWrapperModel> defaultClass = wrappingProperties.getWrapperClass();

        if (annotatedWrapperClass == null
                || (annotatedWrapperClass == WrapperResponseMessage.class && defaultClass != null)) return defaultClass;

        return annotatedWrapperClass;
    }
}

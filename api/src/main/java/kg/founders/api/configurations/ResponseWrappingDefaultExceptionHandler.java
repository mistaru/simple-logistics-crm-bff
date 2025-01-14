package kg.founders.api.configurations;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import kg.founders.api.configurations.properties.ResponseWrappingProperties;
import kg.founders.common.models.wrapper.ResponseWrapperModel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Set;

@Slf4j
@ControllerAdvice
@Order(ResponseWrappingDefaultExceptionHandler.DEFAULT_EXCEPTION_HANDLER_SORT)
public class ResponseWrappingDefaultExceptionHandler {
    public static final int DEFAULT_EXCEPTION_HANDLER_SORT = 200;

    @Autowired
    private ResponseWrappingProperties wrappingProperties;

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ResponseWrapperModel> serverError(Throwable e) {
        HttpStatus initialStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        HttpStatus responseStatus = initialStatus;

        ResponseStatus responseStatusAnnotation = e.getClass().getAnnotation(ResponseStatus.class);
        if (responseStatusAnnotation != null)
            responseStatus = responseStatusAnnotation.value();

        if (e instanceof HttpStatusAware && ((HttpStatusAware) e).getStatus() != null)
            responseStatus = ((HttpStatusAware) e).getStatus();

        return this.prepareResponse(e, responseStatus, responseStatus == initialStatus);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ResponseWrapperModel> handleError(MissingServletRequestParameterException e) {
        log.warn("Missing Request Parameter", e);
        String message = String.format("Missing Request Parameter: %s", e.getParameterName());

        ResponseWrapperModel responseWrapper = this.prepareResponse(message);
        return new ResponseEntity<>(responseWrapper, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseWrapperModel> handleError(MethodArgumentTypeMismatchException e) {
        log.warn("Method Argument Type Mismatch", e);
        String message = String.format("Method Argument Type Mismatch: %s", e.getName());

        ResponseWrapperModel responseWrapper = this.prepareResponse(message);
        return new ResponseEntity<>(responseWrapper, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseWrapperModel> handleError(MethodArgumentNotValidException e) {
        log.warn("Method Argument Not Valid", e);
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String message = String.format("%s:%s", error.getField(), error.getDefaultMessage());

        ResponseWrapperModel responseWrapper = this.prepareResponse(message);
        return new ResponseEntity<>(responseWrapper, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    public  ResponseEntity<ResponseWrapperModel> handleError(BindException e) {
        log.warn("Bind Exception", e);
        FieldError error = e.getFieldError();
        String message = String.format("%s:%s", error.getField(), error.getDefaultMessage());

        ResponseWrapperModel responseWrapper = this.prepareResponse(message);
        return new ResponseEntity<>(responseWrapper, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public  ResponseEntity<ResponseWrapperModel> handleError(ConstraintViolationException e) {
        log.warn("Constraint Violation", e);
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String path = ((PathImpl) violation.getPropertyPath()).getLeafNode().getName();
        String message = String.format("%s:%s", path, violation.getMessage());

        ResponseWrapperModel responseWrapper = this.prepareResponse(message);
        return new ResponseEntity<>(responseWrapper, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public  ResponseEntity<ResponseWrapperModel> handleError(HttpMessageNotReadableException e) {
        log.warn("HttpMessageNotReadableException ", e);
        String message = String.format("HttpMessageNotReadable:%s", e.getMessage());

        ResponseWrapperModel responseWrapper = this.prepareResponse(message);
        return new ResponseEntity<>(responseWrapper, HttpStatus.BAD_REQUEST);
    }

    @SneakyThrows
    private ResponseEntity<ResponseWrapperModel> prepareResponse(Throwable e, HttpStatus httpStatus, boolean isFatalException) {
        log.error("Error occurred", e);

        ResponseWrapperModel responseWrapper = this.prepareResponse(e.getMessage(), isFatalException);
        return new ResponseEntity<>(responseWrapper, httpStatus);
    }

    @SneakyThrows
    private ResponseWrapperModel prepareResponse(String message, boolean isFatalException) {

        ResponseWrapperModel wrapperModel = wrappingProperties.getWrapperClass().getDeclaredConstructor().newInstance();
        wrapperModel.setResultCode(isFatalException ? wrapperModel.extractExceptionCode() : wrapperModel.extractFailCode());
        wrapperModel.setErrorMessage(message);

        return wrapperModel;
    }

    @SneakyThrows
    private ResponseWrapperModel prepareResponse(String message) {
        return this.prepareResponse(message, false);
    }

}

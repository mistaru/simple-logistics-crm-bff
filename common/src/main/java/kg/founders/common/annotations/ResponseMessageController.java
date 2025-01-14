package kg.founders.common.annotations;

import kg.founders.common.models.wrapper.ResponseWrapperModel;
import kg.founders.common.models.wrapper.WrapperResponseMessage;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@RestController
@RequestMapping
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ResponseMessageController {
    Class<? extends ResponseWrapperModel> wrapperClass() default WrapperResponseMessage.class;

    @AliasFor(annotation = RequestMapping.class, value = "path")
    String[] value() default {};

    @AliasFor(annotation = RequestMapping.class, value = "value")
    String[] path() default {};
}

package kg.founders.common.models.wrapper;

import kg.founders.common.enums.WrapperResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WrapperResponseMessage<T> implements Serializable, ResponseWrapperModel<T, WrapperResultCode> {
    private T result;

    private WrapperResultCode resultCode;

    private String details;

    private String message;

    @Override
    public void setErrorMessage(String errorMessage) {
        this.details = errorMessage;
    }

    @Override
    public WrapperResultCode extractResultCodeType() {
        return WrapperResultCode.SUCCESS;
    }

    @Override
    public WrapperResultCode extractExceptionCode() {
        return  WrapperResultCode.EXCEPTION;
    }

    @Override
    public WrapperResultCode extractFailCode() {
        return WrapperResultCode.FAIL;
    }
}

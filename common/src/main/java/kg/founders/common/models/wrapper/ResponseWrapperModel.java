package kg.founders.common.models.wrapper;

public interface ResponseWrapperModel<T, C extends Enum> {
    void setResult(T result);
    void setResultCode(C result);
    void setErrorMessage(String errorMessage);

    C extractResultCodeType();
    C extractExceptionCode();
    C extractFailCode();
}

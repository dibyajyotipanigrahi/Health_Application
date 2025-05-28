package in.ashokit.authservice.exceptionhandler;

public class AuthServiceException extends RuntimeException {

    private final String errCode;

    public AuthServiceException(String message, String errCode) {
        super(message);
        this.errCode = errCode;
    }

    public String getErrCode() {
        return errCode;
    }
}

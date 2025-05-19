package in.ashokit.AuthService.ExceptionHandler;

public class AuthServiceException extends RuntimeException{


    private String errCode;


    public AuthServiceException(String message, String errCode) {
        super(message);
        this.errCode = errCode;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }
}

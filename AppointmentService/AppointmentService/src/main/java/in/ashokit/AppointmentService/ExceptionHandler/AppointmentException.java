package in.ashokit.AppointmentService.ExceptionHandler;

public class AppointmentException extends RuntimeException{

    private String errCode;

    public AppointmentException(String message, String errCode) {
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

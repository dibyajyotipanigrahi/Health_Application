package in.ashokit.appointmentservice.exceptionhandler;

public class AppointmentException extends RuntimeException{

    private final String errCode;

    public AppointmentException(String message, String errCode) {
        super(message);
        this.errCode = errCode;
    }

    public String getErrCode() {
        return errCode;
    }


}

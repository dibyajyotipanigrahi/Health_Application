package in.ashokit.doctorservice.exceptionhandler;

public class DoctorException extends RuntimeException{

    private final Integer errCode;
    public DoctorException(String message,Integer errCode) {
        super(message);
        this.errCode=errCode;
    }

    public Integer getErrCode() {
        return errCode;
    }


}

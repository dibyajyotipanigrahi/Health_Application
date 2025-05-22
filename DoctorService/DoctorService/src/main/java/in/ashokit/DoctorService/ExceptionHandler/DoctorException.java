package in.ashokit.DoctorService.ExceptionHandler;

public class DoctorException extends RuntimeException{

    private Integer errCode;
    public DoctorException(String message,Integer errCode) {
        super(message);
        this.errCode=errCode;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }
}

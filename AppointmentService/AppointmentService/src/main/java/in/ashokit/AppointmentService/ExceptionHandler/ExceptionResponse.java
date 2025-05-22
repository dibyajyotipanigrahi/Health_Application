package in.ashokit.AppointmentService.ExceptionHandler;

public class ExceptionResponse {

    private String errCode;
    private String errMsg;

    public ExceptionResponse() {

    }

    public ExceptionResponse(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}

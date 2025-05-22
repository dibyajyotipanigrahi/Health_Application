package in.ashokit.DoctorService.ApiResponse;

public class ApiResponse <T>{

    private String message;
    private Integer statusCode;
    private T data;

    public ApiResponse() {
    }

    public ApiResponse(String message, Integer statusCode, T data) {
        this.message = message;
        this.statusCode = statusCode;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

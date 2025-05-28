package in.ashokit.appointmentservice.apiresponse;

public class ApiResponse<T> {

    private Integer status;
    private String message;
    private T data;


    public ApiResponse() {
    }

    public ApiResponse(T data, String message, Integer status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}

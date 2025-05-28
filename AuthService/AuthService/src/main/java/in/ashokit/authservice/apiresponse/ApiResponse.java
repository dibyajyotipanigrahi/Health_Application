package in.ashokit.authservice.apiresponse;

import org.springframework.stereotype.Component;

@Component
public class ApiResponse<T> {

    private String message;
    private T data;
    private Integer status;

    public ApiResponse(String message, T data, Integer status) {
        this.message = message;
        this.data= data;
        this.status = status;
    }

    public ApiResponse() {

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

package in.ashokit.AuthService.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthServiceExceptionHandler {
@ExceptionHandler(value = AuthServiceException.class)
    public ResponseEntity<ExceptionResponse>  handleException(AuthServiceException ae){

    ExceptionResponse exceptionResponse = new ExceptionResponse();
    exceptionResponse.setErrMsg(ae.getMessage());
    exceptionResponse.setErrCode(ae.getErrCode());

    return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }



}

package in.ashokit.appointmentservice.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppointementExceptionHandler {

    @ExceptionHandler(value = AppointmentException.class)
    public ResponseEntity<ExceptionResponse> appoimentExceptionHandler(AppointmentException ae) {
        ExceptionResponse er = new ExceptionResponse();
        er.setErrCode(ae.getErrCode());
        er.setErrMsg(ae.getMessage());
        return new ResponseEntity<>(er, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}

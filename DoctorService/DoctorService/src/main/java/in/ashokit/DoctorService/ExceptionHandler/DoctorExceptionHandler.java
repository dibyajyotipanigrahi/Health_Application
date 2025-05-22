package in.ashokit.DoctorService.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DoctorExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> exceptionHandler(DoctorException de) {

        ExceptionResponse er = new ExceptionResponse();
        er.setErrMsg(de.getMessage());
        er.setErrCode(de.getErrCode());
        return new ResponseEntity<>(er, HttpStatus.OK);
    }
}

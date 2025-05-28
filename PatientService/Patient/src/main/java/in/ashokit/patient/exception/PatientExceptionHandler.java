package in.ashokit.patient.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PatientExceptionHandler {

    @ExceptionHandler(PatientException.class)
    public ResponseEntity<ExResponse>    patientExceptionHandler(PatientException pe){

        ExResponse exResponse = new ExResponse();
        exResponse.setErrCode(pe.getErrCode());
        exResponse.setErrMsg(pe.getMessage());


        return new ResponseEntity<>(exResponse, HttpStatus.OK);
    }
}

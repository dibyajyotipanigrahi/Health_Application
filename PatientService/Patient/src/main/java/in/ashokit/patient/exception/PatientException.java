package in.ashokit.patient.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class PatientException extends RuntimeException {

    private final String errCode;



    public PatientException(String message,String errCode){
        super(message);
        this.errCode=errCode;
    }


}

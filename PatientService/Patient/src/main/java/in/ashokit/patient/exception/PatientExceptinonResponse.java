package in.ashokit.patient.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientExceptinonResponse {

    private String errMsg;
    private Integer errCode;
}

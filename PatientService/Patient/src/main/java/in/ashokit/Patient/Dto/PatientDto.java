package in.ashokit.Patient.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientDto {

    private String fullName;

    private String email;

    private String phoneNumber;

    private String gender;

    private int age;

    private String address;
}

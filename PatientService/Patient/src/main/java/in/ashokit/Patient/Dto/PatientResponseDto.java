package in.ashokit.Patient.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientResponseDto {

    private Integer id;

    private String fullName;

    private String email;

    private String phoneNumber;

    private String gender;

    private int age;

    private String address;

    private LocalDate createdDate;

    private LocalDate upDatedDate;


}

package in.ashokit.Patient.Mapper;

import in.ashokit.Patient.Dto.PatientDto;
import in.ashokit.Patient.Dto.PatientResponseDto;
import in.ashokit.Patient.Entity.Patient;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    private final static ModelMapper mapper = new ModelMapper();

    public Patient patientDtoToPatient(PatientDto patientDto) {
        return mapper.map(patientDto, Patient.class);
    }

    public static PatientResponseDto patientToPatientResponseDto(Patient patient) {
        return mapper.map(patient, PatientResponseDto.class);
    }

}

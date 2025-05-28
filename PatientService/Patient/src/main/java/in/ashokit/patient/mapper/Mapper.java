package in.ashokit.patient.mapper;

import in.ashokit.patient.dto.PatientDto;
import in.ashokit.patient.dto.PatientResponseDto;
import in.ashokit.patient.entity.Patient;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public Patient patientDtoToPatient(PatientDto patientDto) {
        return MODEL_MAPPER.map(patientDto, Patient.class);
    }

    public static PatientResponseDto patientToPatientResponseDto(Patient patient) {
        return MODEL_MAPPER.map(patient, PatientResponseDto.class);
    }

}

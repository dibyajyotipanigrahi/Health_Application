package in.ashokit.Patient.Service;

import in.ashokit.Patient.Dto.PatientDto;
import in.ashokit.Patient.Dto.PatientResponseDto;

import java.util.List;


public interface PatientService {

    public PatientResponseDto addPatient(PatientDto patientDto);

    public PatientResponseDto getPatientById(Integer id);

    public List<PatientResponseDto> getAllPatient();

    public PatientResponseDto updatePatient(Integer id, PatientDto patientDto);

    public void deletePatient(Integer id);

}

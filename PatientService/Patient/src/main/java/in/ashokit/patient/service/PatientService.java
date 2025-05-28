package in.ashokit.patient.service;

import in.ashokit.patient.dto.PatientDto;
import in.ashokit.patient.dto.PatientResponseDto;

import java.util.List;


public interface PatientService {

    public PatientResponseDto addPatient(PatientDto patientDto);

    public PatientResponseDto getPatientById(Integer id);

    public List<PatientResponseDto> getAllPatient();

    public PatientResponseDto updatePatient(Integer id, PatientDto patientDto);

    public void deletePatient(Integer id);

}

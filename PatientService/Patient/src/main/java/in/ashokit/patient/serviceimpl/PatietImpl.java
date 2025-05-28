package in.ashokit.patient.serviceimpl;

import in.ashokit.patient.dto.PatientDto;
import in.ashokit.patient.dto.PatientResponseDto;
import in.ashokit.patient.entity.Patient;
import in.ashokit.patient.exception.PatientException;
import in.ashokit.patient.mapper.Mapper;
import in.ashokit.patient.repository.PatientRepo;
import in.ashokit.patient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatietImpl implements PatientService {

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private Mapper mapper;

    @Override
    public PatientResponseDto addPatient(PatientDto patientDto) {
        Patient patient = patientRepo.save(mapper.patientDtoToPatient(patientDto));
        return Mapper.patientToPatientResponseDto(patient);
    }

    @Override
    public PatientResponseDto getPatientById(Integer id) {
        Optional<Patient> byId = patientRepo.findById(id);
        if (byId.isPresent()) {
            Patient patient = byId.get();
            return Mapper.patientToPatientResponseDto(patient);
        } else {
            throw new PatientException("Not found", "500");
        }
    }

    @Override
    public List<PatientResponseDto> getAllPatient() {
        return patientRepo.findAll().stream().map(Mapper::patientToPatientResponseDto).toList();

    }

    @Override
    public PatientResponseDto updatePatient(Integer id, PatientDto patientDto) {
        Optional<Patient> patients = patientRepo.findById(id);
        if (patients.isPresent()) {
            Patient patient = patients.get();
            patient.setAddress(patientDto.getAddress());
            patient.setAge(patientDto.getAge());
            patient.setEmail(patientDto.getEmail());
            patient.setPhoneNumber(patientDto.getPhoneNumber());
            patient.setGender(patientDto.getGender());
            patient.setFullName(patientDto.getFullName());
            return Mapper.patientToPatientResponseDto(patientRepo.save(patient));
        } else {
            throw new PatientException("Not updated", "500");
        }
    }

    @Override
    public void deletePatient(Integer id) {
        patientRepo.deleteById(id);
    }
}

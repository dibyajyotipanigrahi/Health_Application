package in.ashokit.Patient.ServiceImpl;

import in.ashokit.Patient.Dto.PatientDto;
import in.ashokit.Patient.Dto.PatientResponseDto;
import in.ashokit.Patient.Entity.Patient;
import in.ashokit.Patient.Mapper.Mapper;
import in.ashokit.Patient.Repository.PatientRepo;
import in.ashokit.Patient.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatietImpl implements PatientService {

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private Mapper mapper;

    @Override
    public PatientResponseDto addPatient(PatientDto patientDto) {
        Patient patient = patientRepo.save(mapper.patientDtoToPatient(patientDto));
        return mapper.patientToPatientResponseDto(patient);
    }

    @Override
    public PatientResponseDto getPatientById(Integer id) {
        return mapper.patientToPatientResponseDto(patientRepo.findById(id).get());
    }

    @Override
    public List<PatientResponseDto> getAllPatient() {
        return patientRepo.findAll().stream().map(Mapper::patientToPatientResponseDto).toList();

    }

    @Override
    public PatientResponseDto updatePatient(Integer id, PatientDto patientDto) {
        Patient patient = patientRepo.findById(id).get();

        patient.setAddress(patientDto.getAddress());
        patient.setAge(patientDto.getAge());
        patient.setEmail(patientDto.getEmail());
        patient.setPhoneNumber(patientDto.getPhoneNumber());
        patient.setGender(patientDto.getGender());
        patient.setFullName(patientDto.getFullName());
        return mapper.patientToPatientResponseDto(patientRepo.save(patient));

    }

    @Override
    public void deletePatient(Integer id) {
        patientRepo.deleteById(id);
    }
}

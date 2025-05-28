package in.ashokit.doctorservice.serviceimpl;

import in.ashokit.doctorservice.dto.DoctorDto;
import in.ashokit.doctorservice.dto.DoctorResponseDto;
import in.ashokit.doctorservice.entity.Doctor;
import in.ashokit.doctorservice.errmessage.ErrorMessage;
import in.ashokit.doctorservice.exceptionhandler.DoctorException;
import in.ashokit.doctorservice.mapper.DtoMapper;
import in.ashokit.doctorservice.repository.DoctorRepo;
import in.ashokit.doctorservice.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private DtoMapper mapper;

    @Override
    public DoctorResponseDto addDoctor(DoctorDto doctorDto) {
        Doctor doctorEntity = doctorRepo.save(DtoMapper.doctorDtoToDoctor(doctorDto));
        if (doctorEntity.getId() != null) {
            return mapper.doctorToDoctorResponseDto(doctorEntity);
        } else {
            throw new DoctorException("User Not Registered", 500);
        }
    }

    @Override
    public DoctorResponseDto findByIdDoctor(Integer id) {
        Optional<Doctor> optionalDoctor = doctorRepo.findById(id);
        if (optionalDoctor.isPresent()) {
            return mapper.doctorToDoctorResponseDto(optionalDoctor.get());
        } else {
            throw new DoctorException(ErrorMessage.DOCTOR_NOT_FOUND, 404);
        }
    }

    @Override
    public List<DoctorResponseDto> getAllDoctor() {
        return doctorRepo.findAll()
                .stream()
                .map(mapper::doctorToDoctorResponseDto)
                .toList();
    }

    @Override
    public DoctorResponseDto updateDoctor(Integer id, DoctorDto doctorDto) {
        Doctor doctor = doctorRepo.findById(id)
                .orElseThrow(() -> new DoctorException("Doctor with this ID is not present", 404));

        doctor.setName(doctorDto.getName());
        doctor.setEmail(doctorDto.getEmail());
        doctor.setPhone(doctorDto.getPhone());
        doctor.setSpecialization(doctorDto.getSpecialization());

        Doctor updatedDoctor = doctorRepo.save(doctor);
        return mapper.doctorToDoctorResponseDto(updatedDoctor);
    }

    @Override
    public List<DoctorResponseDto> getDoctorsBySpecialization(String specialization) {
        List<Doctor> doctors = doctorRepo.findBySpecialization(specialization);
        return doctors.stream()
                .map(mapper::doctorToDoctorResponseDto)
                .toList();
    }

    @Override
    public void deleteDoctor(Integer id) {
        if (!doctorRepo.existsById(id)) {
            throw new DoctorException("Doctor with this ID is not present", 404);
        }
        doctorRepo.deleteById(id);
    }
}

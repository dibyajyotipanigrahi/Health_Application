package in.ashokit.DoctorService.ServiceImpl;

import in.ashokit.DoctorService.Dto.DoctorDto;
import in.ashokit.DoctorService.Dto.DoctorResponseDto;
import in.ashokit.DoctorService.Entity.Doctor;
import in.ashokit.DoctorService.ExceptionHandler.DoctorException;
import in.ashokit.DoctorService.Mapper.DtoMapper;
import in.ashokit.DoctorService.Repository.DoctorRepo;
import in.ashokit.DoctorService.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private DtoMapper mapper;

    @Override
    public DoctorResponseDto addDoctor(DoctorDto doctorDto) {
        Doctor doctorEntity = doctorRepo.save(mapper.doctorDtoToDoctor(doctorDto));
        if (doctorEntity != null) {
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
            throw new DoctorException("Doctor with this ID is not present", 404);
        }
    }

    @Override
    public List<DoctorResponseDto> getAllDoctor() {
        return doctorRepo.findAll()
                .stream()
                .map(mapper::doctorToDoctorResponseDto)
                .collect(Collectors.toList());
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
                .collect(Collectors.toList());
    }

    @Override
    public void deleteDoctor(Integer id) {
        if (!doctorRepo.existsById(id)) {
            throw new DoctorException("Doctor with this ID is not present", 404);
        }
        doctorRepo.deleteById(id);
    }
}

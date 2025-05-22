package in.ashokit.DoctorService.Mapper;

import in.ashokit.DoctorService.Dto.DoctorDto;
import in.ashokit.DoctorService.Dto.DoctorResponseDto;
import in.ashokit.DoctorService.Entity.Doctor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {

    private final static ModelMapper mapper = new ModelMapper();

    public  static Doctor doctorDtoToDoctor(DoctorDto dto) {
        return mapper.map(dto, Doctor.class);
    }


    public DoctorResponseDto doctorToDoctorResponseDto(Doctor doctor) {
        return mapper.map(doctor, DoctorResponseDto.class);
    }




}

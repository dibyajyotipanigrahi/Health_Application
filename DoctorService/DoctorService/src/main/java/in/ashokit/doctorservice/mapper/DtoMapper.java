package in.ashokit.doctorservice.mapper;

import in.ashokit.doctorservice.dto.DoctorDto;
import in.ashokit.doctorservice.dto.DoctorResponseDto;
import in.ashokit.doctorservice.entity.Doctor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {

    private  static final  ModelMapper mapper = new ModelMapper();

    public  static Doctor doctorDtoToDoctor(DoctorDto dto) {
        return mapper.map(dto, Doctor.class);
    }


    public DoctorResponseDto doctorToDoctorResponseDto(Doctor doctor) {
        return mapper.map(doctor, DoctorResponseDto.class);
    }




}

package in.ashokit.AppointmentService.Mapper;

import in.ashokit.AppointmentService.Dto.AppointmentDto;
import in.ashokit.AppointmentService.Entity.Appointment;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {


    private static ModelMapper mapper = new ModelMapper();


    public DtoMapper() {
        this.mapper = new ModelMapper();
        // Optional: configure strict matching to avoid ambiguous mapping
        this.mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public Appointment dtoToAppointment(AppointmentDto dto) {
        return mapper.map(dto, Appointment.class);
    }

    public static AppointmentDto appointmentToDto(Appointment appointment) {
        return mapper.map(appointment, AppointmentDto.class);
    }

}

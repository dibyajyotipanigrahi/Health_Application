package in.ashokit.appointmentservice.mapper;

import in.ashokit.appointmentservice.dto.AppointmentDto;
import in.ashokit.appointmentservice.dto.AppointmentResponseDto;
import in.ashokit.appointmentservice.entity.Appointment;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {

    private static final ModelMapper mapper;

    static {
        mapper = new ModelMapper();
        // Optional: configure strict matching to avoid ambiguous mapping
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public Appointment dtoToAppointment(AppointmentDto dto) {
        return mapper.map(dto, Appointment.class);
    }

    public static AppointmentResponseDto appointmentToDto(Appointment appointment) {
        return mapper.map(appointment, AppointmentResponseDto.class);
    }
}

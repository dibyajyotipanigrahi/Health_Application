package in.ashokit.AppointmentService.Service;

import in.ashokit.AppointmentService.Dto.AppointmentDto;
import in.ashokit.AppointmentService.Dto.AppointmentResponseDto;

import java.util.List;

public interface AppointementService {


    public AppointmentResponseDto createAppoitment(AppointmentDto appointmentDto);

    public AppointmentResponseDto getAppointmentById(Integer appointmentId);

    public List<AppointmentResponseDto> getAllAPPoitment();

    public List<AppointmentResponseDto> getPendingAppointmentsByDoctorId(Integer doctorId);

    public AppointmentResponseDto UpdateAppoitment(Integer appointmentId, AppointmentDto status);

    public void deleteAppointment(Integer appointmentId);
}

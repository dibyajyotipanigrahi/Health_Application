package in.ashokit.AppointmentService.Service;

import in.ashokit.AppointmentService.Dto.AppointmentDto;

import java.util.List;

public interface AppointementService {


    public AppointmentDto createAppoitment(AppointmentDto appointmentDto);

    public AppointmentDto getAppointmentById(Integer appointmentId);

    public List<AppointmentDto> getAllAPPoitment();

    public List<AppointmentDto> getPendingAppointmentsByDoctorId(Integer doctorId);

    public AppointmentDto UpdateAppoitment(Integer appointmentId, AppointmentDto status);

    public void deleteAppointment(Integer appointmentId);
}

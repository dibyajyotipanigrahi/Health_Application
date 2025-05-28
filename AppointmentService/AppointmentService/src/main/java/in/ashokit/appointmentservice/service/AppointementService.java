package in.ashokit.appointmentservice.service;

import in.ashokit.appointmentservice.dto.AppointmentDto;
import in.ashokit.appointmentservice.dto.AppointmentResponseDto;

import java.util.List;

public interface AppointementService {


    public AppointmentResponseDto createAppoitment(AppointmentDto appointmentDto);

    public AppointmentResponseDto getAppointmentById(Integer appointmentId);

    public List<AppointmentResponseDto> getAllAPPoitment();

    public List<AppointmentResponseDto> getPendingAppointmentsByDoctorId(Integer doctorId);

    public AppointmentResponseDto updateAppoitment(Integer appointmentId, AppointmentDto status);

    public void deleteAppointment(Integer appointmentId);
}

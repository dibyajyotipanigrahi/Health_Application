package in.ashokit.AppointmentService.ServiceImplementation;

import in.ashokit.AppointmentService.Dto.AppointmentDto;
import in.ashokit.AppointmentService.Entity.Appointment;
import in.ashokit.AppointmentService.ExceptionHandler.AppointmentException;
import in.ashokit.AppointmentService.Mapper.DtoMapper;
import in.ashokit.AppointmentService.Repopsitory.AppointmentRepo;
import in.ashokit.AppointmentService.Service.AppointementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ServiceImplementation implements AppointementService {

    @Autowired
    private AppointmentRepo appointmentRepo;

    @Autowired
    private DtoMapper mapper;


    @Override
    @PreAuthorize("hasRole('ADMIN') or (hasRole('DOCTOR') and #doctorId == principal.doctorId)")
    public AppointmentDto createAppoitment(AppointmentDto appointmentDto) {
        Appointment appointment = mapper.dtoToAppointment(appointmentDto);
        appointment.setStatus("PENDING");

        Appointment saved = appointmentRepo.save(appointment);
        return mapper.appointmentToDto(saved);
    }

    @Override
    public AppointmentDto getAppointmentById(Integer appointmentId) {
        Appointment appointment = appointmentRepo.findById(appointmentId)
                .orElseThrow(() -> new AppointmentException("Resource not found","500"));
        return mapper.appointmentToDto(appointment);
    }

    @Override
    public List<AppointmentDto> getAllAPPoitment() {
        return appointmentRepo.findAll()
                .stream()
                .map(DtoMapper::appointmentToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDto> getPendingAppointmentsByDoctorId(Integer doctorId) {
        List<Appointment> pendingAppointments = appointmentRepo
                .findByDoctorIdAndStatus(doctorId, "PENDING");

        return pendingAppointments.stream()
                .map(DtoMapper::appointmentToDto)
                .collect(Collectors.toList());
    }






    @Override
    public AppointmentDto UpdateAppoitment(Integer appointmentId, AppointmentDto status) {
        Appointment appointment = appointmentRepo.findById(appointmentId)
                .orElseThrow(() -> new AppointmentException("Appointment not found","500"));

        appointment.setStatus(status.getStatus().toUpperCase());
        Appointment updated = appointmentRepo.save(appointment);
        return mapper.appointmentToDto(updated);
    }

    @Override
    public void deleteAppointment(Integer appointmentId) {
        Appointment appointment = appointmentRepo.findById(appointmentId)
                .orElseThrow(() -> new AppointmentException("Appointment not found","500"));
        appointmentRepo.delete(appointment);
    }
}

package in.ashokit.appointmentservice.serviceimplementation;

import in.ashokit.appointmentservice.apiresponse.ApiResponse;
import in.ashokit.appointmentservice.dto.AppointmentDto;
import in.ashokit.appointmentservice.dto.AppointmentResponseDto;
import in.ashokit.appointmentservice.dto.DoctorDto;
import in.ashokit.appointmentservice.dto.EmailRequest;
import in.ashokit.appointmentservice.entity.Appointment;
import in.ashokit.appointmentservice.exceptionhandler.AppointmentException;
import in.ashokit.appointmentservice.feignclient.DoctorClient;
import in.ashokit.appointmentservice.feignclient.EmailClient;
import in.ashokit.appointmentservice.mapper.DtoMapper;
import in.ashokit.appointmentservice.repopsitory.AppointmentRepo;
import in.ashokit.appointmentservice.service.AppointementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ServiceImplementation implements AppointementService {

    @Autowired
    private AppointmentRepo appointmentRepo;




    @Autowired
    private DoctorClient doctorClient;

    @Autowired
    private EmailClient emailClient;


    @Override
    //@PreAuthorize("hasRole('ADMIN') or (hasRole('DOCTOR') and #doctorId == principal.doctorId)")
    public AppointmentResponseDto createAppoitment(AppointmentDto appointmentDto) {
        Appointment appointment = mapper.dtoToAppointment(appointmentDto);
        appointment.setStatus("PENDING");

        Appointment saved = appointmentRepo.save(appointment);



        // Call Doctor API using Feign
        ApiResponse<DoctorDto> doctor = doctorClient.getDoctorById(appointment.getDoctorId());

        System.err.println("Calling EmailClient to send email to: " + doctor.getData().getEmail());

        // Send email using Email API via Feign
        EmailRequest emailRequest = new EmailRequest(
                doctor.getData().getEmail(),
                "New Appointment Booked",
                "Dear Dr. " + doctor.getData().getName() + ",\n\nYou have a new appointment with patient "
                        +  " on " + appointment.getDateTime()
        );






        emailClient.sendEmail(emailRequest);
        System.err.println("Email sent!");

        return DtoMapper.appointmentToDto(saved);
    }

    @Override
    public AppointmentResponseDto getAppointmentById(Integer appointmentId) {
        Appointment appointment = appointmentRepo.findById(appointmentId)
                .orElseThrow(() -> new AppointmentException("Resource not found","500"));
        return mapper.appointmentToDto(appointment);
    }

    @Override
    public List<AppointmentResponseDto> getAllAPPoitment() {
        return appointmentRepo.findAll()
                .stream()
                .map(DtoMapper::appointmentToDto)
                .toList();
    }

    @Override
    public List<AppointmentResponseDto> getPendingAppointmentsByDoctorId(Integer doctorId) {
        List<Appointment> pendingAppointments = appointmentRepo
                .findByDoctorIdAndStatus(doctorId, "PENDING");

        return pendingAppointments.stream()
                .map(DtoMapper::appointmentToDto)
                .toList();
    }






    @Override
    public AppointmentResponseDto updateAppoitment(Integer appointmentId, AppointmentDto status) {
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

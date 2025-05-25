package in.ashokit.AppointmentService.ServiceImplementation;

import in.ashokit.AppointmentService.ApiResponse.ApiResponse;
import in.ashokit.AppointmentService.Dto.AppointmentDto;
import in.ashokit.AppointmentService.Dto.AppointmentResponseDto;
import in.ashokit.AppointmentService.Dto.DoctorDto;
import in.ashokit.AppointmentService.Dto.EmailRequest;
import in.ashokit.AppointmentService.Entity.Appointment;
import in.ashokit.AppointmentService.ExceptionHandler.AppointmentException;
import in.ashokit.AppointmentService.FeignClient.DoctorClient;
import in.ashokit.AppointmentService.FeignClient.EmailClient;
import in.ashokit.AppointmentService.Mapper.DtoMapper;
import in.ashokit.AppointmentService.Repopsitory.AppointmentRepo;
import in.ashokit.AppointmentService.Service.AppointementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ServiceImplementation implements AppointementService {

    @Autowired
    private AppointmentRepo appointmentRepo;

    @Autowired
    private DtoMapper mapper;


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

        System.out.println("Calling EmailClient to send email to: " + doctor.getData().getEmail());

        // Send email using Email API via Feign
        EmailRequest emailRequest = new EmailRequest(
                doctor.getData().getEmail(),
                "New Appointment Booked",
                "Dear Dr. " + doctor.getData().getName() + ",\n\nYou have a new appointment with patient "
                        +  " on " + appointment.getDateTime()
        );






        emailClient.sendEmail(emailRequest);
        System.out.println("Email sent!");

        return mapper.appointmentToDto(saved);
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
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentResponseDto> getPendingAppointmentsByDoctorId(Integer doctorId) {
        List<Appointment> pendingAppointments = appointmentRepo
                .findByDoctorIdAndStatus(doctorId, "PENDING");

        return pendingAppointments.stream()
                .map(DtoMapper::appointmentToDto)
                .collect(Collectors.toList());
    }






    @Override
    public AppointmentResponseDto UpdateAppoitment(Integer appointmentId, AppointmentDto status) {
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

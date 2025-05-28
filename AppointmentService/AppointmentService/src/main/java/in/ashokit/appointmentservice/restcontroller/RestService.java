package in.ashokit.appointmentservice.restcontroller;


import in.ashokit.appointmentservice.apiresponse.ApiResponse;
import in.ashokit.appointmentservice.dto.AppointmentDto;
import in.ashokit.appointmentservice.dto.AppointmentResponseDto;
import in.ashokit.appointmentservice.service.AppointementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class RestService {

    @Autowired
    private AppointementService appointementService;

    @PostMapping("/bookAppointment")
    public ResponseEntity<ApiResponse<AppointmentResponseDto>> bookAppointment(@RequestBody AppointmentDto appointmentDto) {
        ApiResponse<AppointmentResponseDto> apiResponse = new ApiResponse<>();
        AppointmentResponseDto appointment = appointementService.createAppoitment(appointmentDto);
        if (appointment != null) {
            apiResponse.setMessage("Success booked");
            apiResponse.setStatus(200);
            apiResponse.setData(appointment);
        } else {
            apiResponse.setMessage("Failed to  book");
            apiResponse.setStatus(500);
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/getById/{appointmentId}")
    public ResponseEntity<ApiResponse<AppointmentResponseDto>> getById(@PathVariable Integer appointmentId) {
        ApiResponse<AppointmentResponseDto> apiResponse = new ApiResponse<>();
        AppointmentResponseDto appointment = appointementService.getAppointmentById(appointmentId);
        if (appointment != null) {
            apiResponse.setMessage("Successfylly retrive");
            apiResponse.setStatus(200);
            apiResponse.setData(appointment);
        } else {
            apiResponse.setMessage("Failed to retrive");
            apiResponse.setStatus(500);
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<AppointmentResponseDto>>> getAllAppointments() {
        ApiResponse<List<AppointmentResponseDto>> apiResponse = new ApiResponse<>();
        List<AppointmentResponseDto> appointments = appointementService.getAllAPPoitment();
        if (appointments != null && !appointments.isEmpty()) {
            apiResponse.setMessage("Successfully got ");
            apiResponse.setStatus(200);
            apiResponse.setData(appointments);
        } else {
            apiResponse.setMessage("No appointments found");
            apiResponse.setStatus(404);
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/pendingAppointment/{doctorId}")
    public ResponseEntity<ApiResponse<List<AppointmentResponseDto>>> getPendingAppointments(@PathVariable Integer doctorId) {
        ApiResponse<List<AppointmentResponseDto>> apiResponse = new ApiResponse<>();
        List<AppointmentResponseDto> appointments = appointementService.getPendingAppointmentsByDoctorId(doctorId);
        if (appointments != null && !appointments.isEmpty()) {
            apiResponse.setMessage("Successfully got pendingAppointment");
            apiResponse.setStatus(200);
            apiResponse.setData(appointments);
        } else {
            apiResponse.setMessage("No pending appointments");
            apiResponse.setStatus(404);
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/updateAppointment/{appointmentId}")
    public ResponseEntity<ApiResponse<AppointmentResponseDto>> updateAppointment(
            @PathVariable Integer appointmentId,
            @RequestBody AppointmentDto dto) {
        ApiResponse<AppointmentResponseDto> apiResponse = new ApiResponse<>();
        AppointmentResponseDto updated = appointementService.updateAppoitment(appointmentId, dto);
        if (updated != null) {
            apiResponse.setMessage("Updated successfully");
            apiResponse.setStatus(200);
            apiResponse.setData(updated);
        } else {
            apiResponse.setMessage("Update failed");
            apiResponse.setStatus(500);
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/deleteAppointment/{appointmentId}")
    public ResponseEntity<ApiResponse<Void>> deleteAppointment(@PathVariable Integer appointmentId) {
        ApiResponse<Void> apiResponse = new ApiResponse<>();
        try {
            appointementService.deleteAppointment(appointmentId);
            apiResponse.setMessage("Deleted successfully");
            apiResponse.setStatus(200);
        } catch (Exception e) {
            apiResponse.setMessage("Deletion failed: " + e.getMessage());
            apiResponse.setStatus(500);
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}

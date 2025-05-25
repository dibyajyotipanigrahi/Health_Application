package in.ashokit.AppointmentService.RestController;

import in.ashokit.AppointmentService.ApiResponse.ApiResponse;
import in.ashokit.AppointmentService.Dto.AppointmentDto;
import in.ashokit.AppointmentService.Dto.AppointmentResponseDto;
import in.ashokit.AppointmentService.Service.AppointementService;
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
    ApiResponse apiResponse = new ApiResponse();

    @PostMapping("/bookAppointment")
    public ResponseEntity<ApiResponse<AppointmentDto>> bookAppoitment(
            @RequestBody
            AppointmentDto appoitmentDto) {
        AppointmentResponseDto appoitment = appointementService.createAppoitment(appoitmentDto);
        if (appoitment != null) {
            apiResponse.setMessage("Success");
            apiResponse.setStatus(200);
            apiResponse.setData(appoitment);
        } else {
            apiResponse.setMessage("Failed");
            apiResponse.setStatus(500);
        }

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    @GetMapping("/getById/{appointmentId}")
    public ResponseEntity<ApiResponse<AppointmentDto>> getById(
            @PathVariable
            Integer appointmentId) {
        AppointmentResponseDto appoitment = appointementService.getAppointmentById(appointmentId);
        if (appoitment != null) {
            apiResponse.setMessage("Success");
            apiResponse.setStatus(200);
            apiResponse.setData(appoitment);
        } else {
            apiResponse.setMessage("Failed");
            apiResponse.setStatus(500);
        }

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<AppointmentDto>> getAllAppoitment(
    ) {
        List<AppointmentResponseDto> appoitment = appointementService.getAllAPPoitment();
        if (appoitment != null) {
            apiResponse.setMessage("Success");
            apiResponse.setStatus(200);
            apiResponse.setData(appoitment);
        } else {
            apiResponse.setMessage("Failed");
            apiResponse.setStatus(500);
        }

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    @GetMapping("/pendingAppoitment/{doctorId}")
    public ResponseEntity<ApiResponse<AppointmentDto>> pendingAppoitment(
            @RequestBody
            @PathVariable Integer doctorId) {
        List<AppointmentResponseDto> appoitment = appointementService.getPendingAppointmentsByDoctorId(doctorId);
        if (appoitment != null) {
            apiResponse.setMessage("Success");
            apiResponse.setStatus(200);
            apiResponse.setData(appoitment);
        } else {
            apiResponse.setMessage("Failed");
            apiResponse.setStatus(500);
        }

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    @PutMapping("/updateAppoitment/{appointmentId}")
    public ResponseEntity<ApiResponse<AppointmentDto>> updateAppoitment(
            @PathVariable Integer appointmentId, @RequestBody AppointmentDto status) {
        AppointmentResponseDto appoitment = appointementService.UpdateAppoitment(appointmentId, status);
        if (appoitment != null) {
            apiResponse.setMessage("Success");
            apiResponse.setStatus(200);
            apiResponse.setData(appoitment);
        } else {
            apiResponse.setMessage("Failed");
            apiResponse.setStatus(500);
        }

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    @DeleteMapping("/deleteAppoitment/{appointmentId}")
    public ResponseEntity<ApiResponse<AppointmentDto>> updateAppoitment(
            @PathVariable Integer appointmentId) {

        try {
            appointementService.deleteAppointment(appointmentId);
            apiResponse.setMessage("Success");
            apiResponse.setStatus(200);

            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            apiResponse.setMessage("Failed");
            apiResponse.setStatus(500);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }


    }


}

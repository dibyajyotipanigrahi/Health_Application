package in.ashokit.doctorservice.restcontroller;

import in.ashokit.doctorservice.apiresponse.ApiResponse;
import in.ashokit.doctorservice.dto.DoctorDto;
import in.ashokit.doctorservice.dto.DoctorResponseDto;
import in.ashokit.doctorservice.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class DoctorRestController {

    @Autowired
    private DoctorService doctorService;


    @PostMapping("/register")
    public ResponseEntity<ApiResponse<DoctorResponseDto>> registerDoctor(@RequestBody DoctorDto doctorDTO) {
        ApiResponse<DoctorResponseDto> apiResponse = new ApiResponse<>();

        DoctorResponseDto doctorDto = doctorService.addDoctor(doctorDTO);
        if (doctorDto != null) {
            apiResponse.setMessage("Successfully Registered Doctor");
            apiResponse.setData(doctorDto);
            apiResponse.setStatusCode(201);
        } else {
            apiResponse.setStatusCode(500);
            apiResponse.setMessage("not saved");
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);


    }

    @GetMapping("/getAllDoctor")
    public ResponseEntity<ApiResponse<List<DoctorResponseDto>>> getAllDoctors() {

        ApiResponse<List<DoctorResponseDto>> apiResponse = new ApiResponse<>();
        List<DoctorResponseDto> allDoctor = doctorService.getAllDoctor();

        if (allDoctor != null) {
            apiResponse.setMessage("Successfully get all doctor");
            apiResponse.setData(allDoctor);
            apiResponse.setStatusCode(201);
        } else {
            apiResponse.setStatusCode(500);
            apiResponse.setMessage("not get");
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ApiResponse<DoctorResponseDto>> getDoctorById(@PathVariable Integer id) {
        ApiResponse<DoctorResponseDto> apiResponse = new ApiResponse<>();

        DoctorResponseDto doctor = doctorService.findByIdDoctor(id);

        if (doctor != null) {
            apiResponse.setMessage("Successfully get by id");
            apiResponse.setData(doctor);
            apiResponse.setStatusCode(201);
        } else {
            apiResponse.setStatusCode(500);
            apiResponse.setMessage("faild to retrive");
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/specialization/{specialization}")
    public ResponseEntity<ApiResponse<List<DoctorResponseDto>>> getDoctorsBySpecialization(@PathVariable String specialization) {
        ApiResponse<List<DoctorResponseDto>> apiResponse = new ApiResponse<>();
        List<DoctorResponseDto> doctors = doctorService.getDoctorsBySpecialization(specialization);

        if (doctors != null) {
            apiResponse.setMessage("Successfully Retrived");
            apiResponse.setData(doctors);
            apiResponse.setStatusCode(201);
        } else {
            apiResponse.setStatusCode(500);
            apiResponse.setMessage("not retrived");
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<DoctorResponseDto>> updateDoctor(@PathVariable Integer id, @RequestBody DoctorDto doctorDTO) {
        ApiResponse<DoctorResponseDto> apiResponse = new ApiResponse<>();
        DoctorResponseDto doctor = doctorService.updateDoctor(id, doctorDTO);


        if (doctor != null) {
            apiResponse.setMessage("Successfully update");
            apiResponse.setData(doctor);
            apiResponse.setStatusCode(201);
        } else {
            apiResponse.setStatusCode(500);
            apiResponse.setMessage("not update");
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteDoctor(@PathVariable Integer id) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        try {
            doctorService.deleteDoctor(id);
            apiResponse.setMessage("Successfully delete");

            apiResponse.setStatusCode(201);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            apiResponse.setStatusCode(500);
            apiResponse.setMessage("not delete");
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }

    }

}

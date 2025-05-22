package in.ashokit.DoctorService.RestController;

import in.ashokit.DoctorService.ApiResponse.ApiResponse;
import in.ashokit.DoctorService.Dto.DoctorDto;
import in.ashokit.DoctorService.Dto.DoctorResponseDto;
import in.ashokit.DoctorService.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DoctorRestController {

    @Autowired
    private DoctorService doctorService;

    ApiResponse apiResponse = new ApiResponse<>();

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<DoctorResponseDto>> registerDoctor(@RequestBody DoctorDto doctorDTO) {
        DoctorResponseDto doctorDto = doctorService.addDoctor(doctorDTO);
        if (doctorDto != null) {
            apiResponse.setMessage("Successfully Registered");
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
        List<DoctorResponseDto> allDoctor = doctorService.getAllDoctor();

        if (allDoctor != null) {
            apiResponse.setMessage("Successfully Registered");
            apiResponse.setData(allDoctor);
            apiResponse.setStatusCode(201);
        } else {
            apiResponse.setStatusCode(500);
            apiResponse.setMessage("not saved");
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);


    }

    @GetMapping("getById/{id}")
    public ResponseEntity<ApiResponse<DoctorResponseDto>> getDoctorById(@PathVariable Integer id) {
        DoctorResponseDto doctor = doctorService.findByIdDoctor(id);

        if (doctor != null) {
            apiResponse.setMessage("Successfully Registered");
            apiResponse.setData(doctor);
            apiResponse.setStatusCode(201);
        } else {
            apiResponse.setStatusCode(500);
            apiResponse.setMessage("not saved");
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/specialization/{specialization}")
    public ResponseEntity<ApiResponse<List<DoctorResponseDto>>> getDoctorsBySpecialization(@PathVariable String specialization) {
        List<DoctorResponseDto> doctors = doctorService.getDoctorsBySpecialization(specialization);

        if (doctors != null) {
            apiResponse.setMessage("Successfully Registered");
            apiResponse.setData(doctors);
            apiResponse.setStatusCode(201);
        } else {
            apiResponse.setStatusCode(500);
            apiResponse.setMessage("not saved");
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ApiResponse<DoctorResponseDto>> updateDoctor(@PathVariable Integer id, @RequestBody DoctorDto doctorDTO) {
        DoctorResponseDto doctor = doctorService.updateDoctor(id, doctorDTO);


        if (doctor != null) {
            apiResponse.setMessage("Successfully Registered");
            apiResponse.setData(doctor);
            apiResponse.setStatusCode(201);
        } else {
            apiResponse.setStatusCode(500);
            apiResponse.setMessage("not saved");
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteDoctor(@PathVariable Integer id) {

        try {
            doctorService.deleteDoctor(id);
            apiResponse.setMessage("Successfully Registered");

            apiResponse.setStatusCode(201);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            apiResponse.setStatusCode(500);
            apiResponse.setMessage("not saved");
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }

    }

}

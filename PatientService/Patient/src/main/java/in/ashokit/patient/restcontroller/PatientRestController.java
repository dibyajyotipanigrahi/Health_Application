package in.ashokit.patient.restcontroller;

import in.ashokit.patient.apiresponse.ApiResponse;
import in.ashokit.patient.dto.PatientDto;
import in.ashokit.patient.dto.PatientResponseDto;
import in.ashokit.patient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientRestController {

    @Autowired
    private PatientService parientService;



    @PostMapping("/register")
    public ResponseEntity<ApiResponse<PatientResponseDto>> rigisterPatient(@RequestBody PatientDto patientDto) {
        ApiResponse<PatientResponseDto> apiResponse = new ApiResponse<>();


        PatientResponseDto patient = parientService.addPatient(patientDto);
        if (patient != null) {
            apiResponse.setMessage("Register");
            apiResponse.setStatus("201");
            apiResponse.setData(patient);
        } else {
            apiResponse.setMessage("not added");
            apiResponse.setStatus("500");
        }

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }


    @GetMapping("/getById/{id}")
    public ResponseEntity<ApiResponse<PatientResponseDto>> getPatientById(@PathVariable Integer id) {
        ApiResponse<PatientResponseDto> apiResponse = new ApiResponse<>();
        PatientResponseDto patient = parientService.getPatientById(id);
        if (patient != null) {
            apiResponse.setMessage("Update");
            apiResponse.setStatus("201");
            apiResponse.setData(patient);
        } else {
            apiResponse.setMessage("not updated");
            apiResponse.setStatus("500");
        }

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }


    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<PatientResponseDto>>> getAllPatient() {

        ApiResponse<List<PatientResponseDto>> apiResponse = new ApiResponse<>();
        List<PatientResponseDto> patient = parientService.getAllPatient();
        if (patient != null) {
            apiResponse.setMessage("All get");
            apiResponse.setStatus("201");
            apiResponse.setData(patient);
        } else {
            apiResponse.setMessage("No one present");
            apiResponse.setStatus("500");
        }

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }


    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<PatientResponseDto>> updatePatient(@PathVariable Integer id, @RequestBody PatientDto patientDto) {

        ApiResponse<PatientResponseDto> apiResponse = new ApiResponse<>();
        PatientResponseDto patient = parientService.updatePatient(id,patientDto);
        if (patient != null) {
            apiResponse.setMessage("update");
            apiResponse.setStatus("201");
            apiResponse.setData(patient);
        } else {
            apiResponse.setMessage("not update");
            apiResponse.setStatus("500");
        }

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

@DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deletePatient(@PathVariable Integer id) {
    ApiResponse<String> apiResponse = new ApiResponse<>();
        try {
            parientService.deletePatient(id);

            apiResponse.setMessage("Delete");
            apiResponse.setStatus("201");

            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            apiResponse.setMessage("not delete");
            apiResponse.setStatus("500");

            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }


    }


}

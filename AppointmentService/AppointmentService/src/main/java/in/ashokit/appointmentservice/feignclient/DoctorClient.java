package in.ashokit.appointmentservice.feignclient;

import in.ashokit.appointmentservice.apiresponse.ApiResponse;
import in.ashokit.appointmentservice.dto.DoctorDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DoctorService", url = "http://localhost:8082")
public interface DoctorClient {

    @GetMapping("/getById/{id}")
    public ApiResponse<DoctorDto> getDoctorById(@PathVariable Integer id);
}

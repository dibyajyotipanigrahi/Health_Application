package in.ashokit.AppointmentService.FeignClient;

import in.ashokit.AppointmentService.Dto.EmailRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "EmailService", url = "http://localhost:4444")
public interface EmailClient {


    @PostMapping("/sendEmail")
    void sendEmail(@RequestBody EmailRequest emailRequest);


}

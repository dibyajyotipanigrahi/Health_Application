package in.ashokit.EmailService.EmailController;

import in.ashokit.EmailService.EntityDto.EmailRequest;
import in.ashokit.EmailService.Service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest) throws MessagingException {

        emailService.sendVerificationEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getBody());
        return new ResponseEntity<>("Email send Successfully", HttpStatus.OK);
    }

}

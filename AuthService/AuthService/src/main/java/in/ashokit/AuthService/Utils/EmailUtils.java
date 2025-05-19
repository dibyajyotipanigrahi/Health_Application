package in.ashokit.AuthService.Utils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailUtils {

    @Autowired
    private JavaMailSender mailsender;


    public String generateVerificationCode() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);

    }

    @Async
    public void sendVerificationEmail(String to, String name, String code) throws MessagingException {
        MimeMessage message = mailsender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject("Email Verification");
        helper.setFrom("dibyajyoti.panigrahi.dipu@gmail.com");

        String html = "<h2>Hello " + name + ",</h2>" +
                "<p>Your verification code is:</p>" +
                "<h3 style='color:blue;'>" + code + "</h3>" +
                "<p>This code will expire in 10 minutes.</p>";
        helper.setText(html, true);
        mailsender.send(message);

    }


}

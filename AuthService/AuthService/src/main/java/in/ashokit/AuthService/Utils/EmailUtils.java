package in.ashokit.AuthService.Utils;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailUtils {




    public String generateVerificationCode() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);

    }

}

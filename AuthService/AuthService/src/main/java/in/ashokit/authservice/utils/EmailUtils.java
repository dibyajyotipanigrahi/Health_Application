package in.ashokit.authservice.utils;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailUtils {

    private static final Random random = new Random();


    public String generateVerificationCode() {

        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);

    }

}

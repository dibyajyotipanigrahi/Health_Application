package in.ashokit.ApiGetwayService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableDiscoveryClient
@CrossOrigin(origins = "*")
public class ApiGetwayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGetwayServiceApplication.class, args);
	}

}

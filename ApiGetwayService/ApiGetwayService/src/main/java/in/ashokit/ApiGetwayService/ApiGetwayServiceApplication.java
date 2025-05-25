package in.ashokit.ApiGetwayService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGetwayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGetwayServiceApplication.class, args);
	}

}

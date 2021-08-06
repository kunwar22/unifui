package in.co.srdt.unif;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class UnifApplication {
	public static void main(String[] args) {
		SpringApplication.run(UnifApplication.class, args);
	}

}

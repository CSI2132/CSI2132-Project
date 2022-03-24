package csi2132.dentist.DentalOffice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class DentalOfficeApplication {
	public static void main(String[] args) {
		SpringApplication.run(DentalOfficeApplication.class, args);
	}
}

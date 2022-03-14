package csi2132.dentist.DentalOffice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class DentalOfficeApplication implements CommandLineRunner {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(DentalOfficeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		List<Map<String, Object>> test = jdbcTemplate.queryForList("SELECT * FROM ARTIST;");
		System.out.println(test.toString());
	}
}

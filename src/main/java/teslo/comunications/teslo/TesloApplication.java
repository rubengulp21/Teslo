package teslo.comunications.teslo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@EnableMongoRepositories
public class TesloApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(TesloApplication.class, args);
	}

}

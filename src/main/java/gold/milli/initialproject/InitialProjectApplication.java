package gold.milli.initialproject;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "financial transaction API",
				version = "1.0",
				description = "My first real spring boot project"
		)
)
public class InitialProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(InitialProjectApplication.class, args);
	}

}

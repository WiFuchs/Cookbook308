package littlechef;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@Configuration
@ComponentScan
@EnableJpaRepositories("littlechef.repositories")
@EntityScan("littlechef.entities")
public class LittleChefApplication {

	public static void main(String[] args) {
		if(args == null) {
			System.out.println("Command-line arguments passed to Spring are null");
			System.exit(0);
		}
		else {
			SpringApplication.run(LittleChefApplication.class, args);
		}
	}

	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
}

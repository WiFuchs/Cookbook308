package littlechef;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// This is the main application class, which runs the main application and creates
// the PasswordEncoder Bean. The Annotations above the class declaration scan the
// subpackages for entities and repositories for Spring dependency injection


@SpringBootApplication
@Configuration
@ComponentScan
@EnableJpaRepositories("littlechef.repositories")
@EntityScan("littlechef.entities")
public class LittleChefApplication {

	public static void main(String[] args) {
		SpringApplication.run(LittleChefApplication.class, args);
	}

	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

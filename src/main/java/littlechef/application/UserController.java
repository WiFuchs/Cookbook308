package littlechef.application;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import littlechef.entities.ApplicationUser;
import littlechef.exceptions.DuplicateUserException;
import littlechef.repositories.ApplicationUserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

    private ApplicationUserRepository applicationUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(ApplicationUserRepository applicationUserRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/sign-up")
    public void signUp(@RequestBody ApplicationUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if (applicationUserRepository.findByUsername(user.getUsername()) != null) {
        	throw new DuplicateUserException(user.getUsername());
        }
        applicationUserRepository.save(user);
    }
    
    @GetMapping("/current")
    ApplicationUser current(@AuthenticationPrincipal String user) {
		ApplicationUser usr = applicationUserRepository.findByUsername(user);
		usr.setPassword("");
		return usr;
    }
}
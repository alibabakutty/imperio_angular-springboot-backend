package imperio.imperio_backend.login.service;

import imperio.imperio_backend.exception.userNotFoundException.UserNotFoundException;
import imperio.imperio_backend.login.dto.LoginDTO;
import imperio.imperio_backend.login.module.LoginModule;
import imperio.imperio_backend.login.repository.LoginRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAccess {

    private final LoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;

    public UserAccess(LoginRepository loginRepository, PasswordEncoder passwordEncoder) {
        this.loginRepository = loginRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean validateLogin(LoginDTO loginDTO) {
        LoginModule user = loginRepository.findByUsername(loginDTO.getUsername()).orElseThrow(() -> new UserNotFoundException("User not found: " + loginDTO.getUsername()));

        return passwordEncoder.matches(loginDTO.getPassword(), user.getPassword());
    }

    public boolean userExists(String username) {
        return loginRepository.findByUsername(username).isPresent();
    }
}

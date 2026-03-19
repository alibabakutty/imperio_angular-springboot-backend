package imperio.imperio_backend.login.service;

import imperio.imperio_backend.login.dto.LoginDTO;
import imperio.imperio_backend.login.module.LoginModule;
import imperio.imperio_backend.login.repository.LoginRepository;
import imperio.imperio_backend.security.JwtUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {
    private final JwtUtils jwtUtils;
    private final UserAccess userAccess;
    private final LoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginService(JwtUtils jwtUtils, UserAccess userAccess, LoginRepository loginRepository, PasswordEncoder passwordEncoder){
        this.jwtUtils = jwtUtils;
        this.userAccess = userAccess;
        this.loginRepository = loginRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<?> loginService(LoginDTO loginDTO){
        boolean loginValid = userAccess.validateLogin(loginDTO);

        if (loginValid) {
            ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(loginDTO.getUsername());

            Map<String, String> tokenValue = new HashMap<>();
            tokenValue.put("token", jwtCookie.getValue());

            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body(tokenValue);
        } else {
            return ResponseEntity.badRequest().body("Login Failed");
        }
    }

    public ResponseEntity<?> register(LoginDTO loginDTO) {
        // check if user already exists
        if (userAccess.userExists(loginDTO.getUsername())) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        LoginModule user = new LoginModule();
        user.setUsername(loginDTO.getUsername());
        user.setPassword(passwordEncoder.encode(loginDTO.getPassword()));
        user.setEmail(loginDTO.getEmail());
        user.setRole(loginDTO.getRole());

        loginRepository.save(user);

        return ResponseEntity.ok("User registered successfully");
    }
}

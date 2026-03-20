package imperio.imperio_backend.login.controller;

import imperio.imperio_backend.login.dto.LoginDTO;
import imperio.imperio_backend.login.dto.UserProfileDTO;
import imperio.imperio_backend.login.service.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<?> loginController(@Valid @RequestBody LoginDTO loginDTO){
        // No try-catch needed! GlobalExceptionHandler handles any failures.
        return loginService.login(loginDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerController(@Valid @RequestBody LoginDTO loginDTO){
        return loginService.register(loginDTO);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutController(){
        return loginService.logout();
    }

    @GetMapping("/me")
    public ResponseEntity<UserProfileDTO> getCurrentUser(Principal principal){
        // Principal is the standard way to get the logged-in user from Spring Security
        return loginService.getCurrentUser(principal.getName());
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(Principal principal, @RequestParam String oldPassword, @RequestParam String newPassword){
        return loginService.changePassword(principal.getName(), oldPassword, newPassword);
    }
}

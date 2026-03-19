package imperio.imperio_backend.login.controller;

import imperio.imperio_backend.login.dto.LoginDTO;
import imperio.imperio_backend.login.service.LoginService;
import imperio.imperio_backend.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private LoginService loginService;

    @Autowired
    public JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> loginController(@RequestBody LoginDTO loginDTO){
        try{
            return loginService.loginService(loginDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerController(@RequestBody LoginDTO loginDTO){
        return loginService.register(loginDTO);
    }
}

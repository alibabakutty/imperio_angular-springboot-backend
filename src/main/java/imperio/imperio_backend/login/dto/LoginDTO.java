package imperio.imperio_backend.login.dto;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LoginDTO {

    private String username;

    private String password;

    private String email;

    private String role;
}

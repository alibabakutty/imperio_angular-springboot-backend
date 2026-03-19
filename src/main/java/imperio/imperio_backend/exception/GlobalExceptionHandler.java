package imperio.imperio_backend.exception;

import imperio.imperio_backend.exception.exceptionUser.AccessDenied;
import imperio.imperio_backend.exception.loginException.UserNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFound ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex){
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccessDenied.class)
    public ResponseEntity<String> handleAccessDenied(AccessDenied ex){
        return new ResponseEntity<>(
                "You do not have permission to access this resource", HttpStatus.FORBIDDEN
        );
    }
}

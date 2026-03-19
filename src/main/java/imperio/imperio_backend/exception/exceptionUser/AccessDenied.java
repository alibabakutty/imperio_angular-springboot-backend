package imperio.imperio_backend.exception.exceptionUser;

public class AccessDenied extends RuntimeException{
    public AccessDenied(String message){
        super(message);
    }
}

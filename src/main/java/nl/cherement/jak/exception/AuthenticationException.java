package nl.cherement.jak.exception;

public class AuthenticationException extends RuntimeException{

    public AuthenticationException(String message){
        super(message);
    }
}
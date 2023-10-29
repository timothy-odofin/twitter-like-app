package odofin.oyejide.twitterlikeapp.model.dto.response.exception;

public class InvalidTokenException extends RuntimeException{
    private String message;
    private String token;

    public InvalidTokenException(String message, String token) {
        super(message);
        this.message = message;
        this.token = token;
    }

    public InvalidTokenException(String token) {
        this.token = token;
    }

    public InvalidTokenException() {

    }

    public String getToken() {
        return token;
    }
}

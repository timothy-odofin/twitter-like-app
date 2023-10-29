package odofin.oyejide.twitterlikeapp.model.dto.response.exception;

public class UserNotFoundException extends RuntimeException{
    private String message;
    public UserNotFoundException(String message){
        super(message);
        this.message=message;
    }
    public UserNotFoundException(){

    }

}

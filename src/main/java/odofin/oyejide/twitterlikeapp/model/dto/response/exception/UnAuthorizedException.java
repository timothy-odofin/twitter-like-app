package odofin.oyejide.twitterlikeapp.model.dto.response.exception;

import odofin.oyejide.twitterlikeapp.model.dto.TokenDetails;

public class UnAuthorizedException extends RuntimeException {

    private final TokenDetails response;

    public UnAuthorizedException(TokenDetails response) {
        super(response.getMessage());
        this.response = response;
    }

    public TokenDetails getResponse() {
        return response;
    }
}

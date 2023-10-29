package odofin.oyejide.twitterlikeapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDetails {
    private String token;
   private  boolean isTokenValid;
    private String role;
    private boolean authorizedUser;
    private String message;
    private int code;
}

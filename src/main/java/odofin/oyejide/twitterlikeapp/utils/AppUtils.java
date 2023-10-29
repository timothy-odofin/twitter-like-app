package odofin.oyejide.twitterlikeapp.utils;

import odofin.oyejide.twitterlikeapp.model.dto.TokenDetails;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

public class AppUtils {
    public static String generateToken(Integer userId, String role) {
        String tokenString = role + ":" + userId;
        String encodedTokenString = encode(tokenString);
        String uuid = UUID.randomUUID().toString();
        return encode(uuid + ":" + encodedTokenString);
    }
    private static String encode(String data){
        return Base64.getEncoder().encodeToString(data.getBytes(StandardCharsets.UTF_8));
    }
    private static String decode(String data){
        return new String(Base64.getDecoder().decode(data), StandardCharsets.UTF_8);
    }

    public static TokenDetails decodeToken(String token, String currentEndpoint) {
        try{
            String[] parts =decode(token).split(":");
            String encodedTokenString = parts[1];
            String decodedTokenString =decode(encodedTokenString);
            String[] tokenParts = decodedTokenString.split(":");
            String roleName=tokenParts[0];
               return roleName.equalsIgnoreCase(tokenParts[0])? TokenDetails.builder()
                       .isTokenValid(true)
                       .authorizedUser(true)
                       .role(roleName)
                       .token(token)
                       .build():TokenDetails.builder()
                       .isTokenValid(true)
                       .code(403)
                       .authorizedUser(allowedEndpoint(currentEndpoint.substring(1)))
                       .role(roleName)
                       .token(token)
                       .build();

        }catch (Exception e){

         return   TokenDetails.builder()
                    .isTokenValid(false)
                 .authorizedUser(false)
                    .authorizedUser(false)
                    .role(null)
                 .code(401)
                    .token(token)
                 .message(MessageUtil.INVALID_TOKEN)
                    .build();
        }
    }
    private static Boolean allowedEndpoint(String currentEndpoint){
     return List.of("getUserMessages","subscribe").contains(currentEndpoint);

    }

}

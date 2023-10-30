package odofin.oyejide.twitterlikeapp.utils;

import odofin.oyejide.twitterlikeapp.model.dto.TokenDetails;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

public class AppUtils {
    public final static List<String> USER_ROLES = List.of("publisher", "subscriber");
    /**
     * Generates a token using the provided user ID and role.
     *
     * @param userId The user's ID.
     * @param role   The user's role.
     * @return The generated token.
     */
    public static String generateToken(Integer userId, String role) {
        // Combine role and userId to create a token string
        String tokenString = role + ":" + userId;

        // Encode the token string using Base64 encoding
        String encodedTokenString = encode(tokenString);

        // Generate a UUID and append the encoded token string
        String uuid = UUID.randomUUID().toString();

        // Encode the UUID + encoded token string
        return encode(uuid + ":" + encodedTokenString);
    }

    /**
     * Encodes a given string using Base64 encoding.
     *
     * @param data The string to encode.
     * @return The encoded string.
     */
    private static String encode(String data){
        return Base64.getEncoder().encodeToString(data.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Decodes a given string using Base64 decoding.
     *
     * @param data The string to decode.
     * @return The decoded string.
     */
    private static String decode(String data){
        return new String(Base64.getDecoder().decode(data), StandardCharsets.UTF_8);
    }

    /**
     * Decodes a token and checks if it is valid for the current endpoint.
     *
     * @param token            The token to decode.
     * @param currentEndpoint  The current endpoint being accessed.
     * @return TokenDetails object indicating token validity, authorization, and role.
     */
    public static TokenDetails decodeToken(String token, String currentEndpoint) {
        try{
            // Split the token into parts and decode it
            String[] parts = decode(token).split(":");
            String encodedTokenString = parts[1];
            String decodedTokenString = decode(encodedTokenString);
            String[] tokenParts = decodedTokenString.split(":");
            String roleName = tokenParts[0];

            // Check if the provided role matches the decoded role
            return roleName.equalsIgnoreCase(tokenParts[0]) ?
                    TokenDetails.builder()
                            .isTokenValid(true)
                            .authorizedUser(true)
                            .role(roleName)
                            .token(token)
                            .build() :
                    TokenDetails.builder()
                            .isTokenValid(true)
                            .code(403)
                            .authorizedUser(allowedEndpointForSubscribers(currentEndpoint.substring(1)))
                            .role(roleName)
                            .token(token)
                            .build();

        } catch (Exception e){
            // Handle exceptions (e.g., invalid token)
            return TokenDetails.builder()
                    .isTokenValid(false)
                    .authorizedUser(false)
                    .role(null)
                    .code(401)
                    .token(token)
                    .message(MessageUtil.INVALID_TOKEN)
                    .build();
        }
    }
    private static Boolean allowedEndpointForSubscribers(String currentEndpoint){
     return List.of("getUserMessages","subscribe").contains(currentEndpoint);

    }

}

package odofin.oyejide.twitterlikeapp.utils;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class AppUtils {
    public static String generateToken(String username, Integer userId) {
        String tokenString = username + userId;
        byte[] tokenBytes = tokenString.getBytes(StandardCharsets.UTF_8);
        return UUID.nameUUIDFromBytes(tokenBytes).toString();
    }
}

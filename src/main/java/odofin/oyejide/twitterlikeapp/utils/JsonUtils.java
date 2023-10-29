package odofin.oyejide.twitterlikeapp.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

    public static <T> String toJson(T object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    public static <T> String toJsonSafe(T object) {
        try {
            return toJson(object);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}

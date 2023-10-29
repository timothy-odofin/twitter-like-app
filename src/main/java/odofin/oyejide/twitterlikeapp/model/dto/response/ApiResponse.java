package odofin.oyejide.twitterlikeapp.model.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @param <T>
 * @author odofintimothy
 */
@Data
@Builder
@NoArgsConstructor
public class ApiResponse<T> {
    private String responseMessage;
    private int responseCode;
    private T data;

    public ApiResponse(String responseMessage, T data) {
        this.responseMessage = responseMessage;
        this.data = data;
    }

    public ApiResponse(String responseMessage, int responseCode, T data) {
        this.responseMessage = responseMessage;
        this.data = data;
        this.responseCode = responseCode;
    }

}

package odofin.oyejide.twitterlikeapp.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubscribeResponse {
    private Integer responseCode;
    private String responseMessage;
}

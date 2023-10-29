package odofin.oyejide.twitterlikeapp.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import odofin.oyejide.twitterlikeapp.validator.NotNullOrEmpty;
import odofin.oyejide.twitterlikeapp.validator.NotNullOrMin;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PublishMessageRequest {
    @NotNullOrMin
    private Integer userId;
    @NotNullOrEmpty
    private String message;

}

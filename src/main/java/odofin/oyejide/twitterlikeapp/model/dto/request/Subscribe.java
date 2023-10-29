package odofin.oyejide.twitterlikeapp.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import odofin.oyejide.twitterlikeapp.validator.NotNullOrMin;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Subscribe {
    @NotNullOrMin
    private Integer userId;
    @NotNullOrMin
    private Integer subscriberID;

}

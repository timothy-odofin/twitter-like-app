package odofin.oyejide.twitterlikeapp.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Subscribe {
    private Integer userId;
    private Integer subscriberID;

}

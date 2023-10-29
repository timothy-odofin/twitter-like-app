package odofin.oyejide.twitterlikeapp.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponses {
    private Integer code;
    private Integer token;
    private Integer status;
    private String message;

}

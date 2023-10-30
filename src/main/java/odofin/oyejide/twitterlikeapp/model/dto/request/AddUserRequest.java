package odofin.oyejide.twitterlikeapp.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import odofin.oyejide.twitterlikeapp.validator.NotNullOrEmpty;
import odofin.oyejide.twitterlikeapp.validator.ValidRole;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserRequest {
    @NotNullOrEmpty
    private String uName;
    @ValidRole
    private String uRole;
}

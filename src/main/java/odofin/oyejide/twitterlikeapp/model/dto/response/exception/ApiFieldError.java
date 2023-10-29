package odofin.oyejide.twitterlikeapp.model.dto.response.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiFieldError {
    private String field;
    private String code;
    private Object rejectedValue;
    private String message;

}

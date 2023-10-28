package odofin.oyejide.twitterlikeapp.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
@Getter
@Setter
public abstract class BaseEntity {

    @Id
    private Integer id;

}

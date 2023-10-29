package odofin.oyejide.twitterlikeapp.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("T_messages")
@Getter
@Setter
public class Message {

    @Id
    private Integer mid;
    private Integer uid;
    private String contents;

}

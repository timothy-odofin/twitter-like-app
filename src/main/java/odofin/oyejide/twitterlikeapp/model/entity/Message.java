package odofin.oyejide.twitterlikeapp.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

@Table("T_messages")
@Getter
@Setter
public class Message extends BaseEntity {

    private Integer uid;
    private String contents;


}

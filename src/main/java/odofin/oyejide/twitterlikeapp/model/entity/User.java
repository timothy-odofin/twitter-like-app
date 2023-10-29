package odofin.oyejide.twitterlikeapp.model.entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("T_users")
@Getter
@Setter
public class User {

    @Id
    private Integer uid;
    private String uName;
    private String uRole;

}


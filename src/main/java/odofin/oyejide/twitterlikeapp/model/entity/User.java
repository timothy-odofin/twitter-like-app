package odofin.oyejide.twitterlikeapp.model.entity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("T_users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private Integer uid;
    private String uName;
    private String uRole;

}


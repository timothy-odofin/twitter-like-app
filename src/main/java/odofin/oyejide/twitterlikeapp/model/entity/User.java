package odofin.oyejide.twitterlikeapp.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

@Table("T_users")
@Getter
@Setter
public class User extends BaseEntity {

    private String uName;
    private String uRole;
}

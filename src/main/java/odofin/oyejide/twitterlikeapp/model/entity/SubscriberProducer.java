package odofin.oyejide.twitterlikeapp.model.entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("SubscriberProducer")
@Getter
@Setter
public class SubscriberProducer {

    @Id
    private Integer spId;
    private Integer subcriberId;
    private Integer producerId;

}

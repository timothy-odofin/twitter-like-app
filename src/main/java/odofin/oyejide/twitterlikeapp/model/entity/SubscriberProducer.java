package odofin.oyejide.twitterlikeapp.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

@Table("SubscriberProducer")
@Getter
@Setter
public class SubscriberProducer extends BaseEntity {

    private Integer subcriberId;
    private Integer producerId;

}

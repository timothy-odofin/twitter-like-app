package odofin.oyejide.twitterlikeapp.model.entity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("SubscriberProducer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriberProducer {

    @Id
    @Column("SP_ID")
    private Integer spId;
    @Column("subscriberId")
    private Integer subscriberId;
    @Column("producerId")
    private Integer producerId;

}

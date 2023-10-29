package odofin.oyejide.twitterlikeapp.repository;

import odofin.oyejide.twitterlikeapp.model.entity.SubscriberProducer;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface SubscriberProducerRepository extends ReactiveCrudRepository<SubscriberProducer,Integer> {
    @Query(value="select producerId from SubscriberProducer  where subscriberId=:subscriberId")
    Flux<Integer> listProducerBySubscriber(@Param("subscriberId") Integer subscriberId);
    @Query(value="select * from SubscriberProducer  where subscriberId=:subscriberId and producerId=:producerId")
    Flux<SubscriberProducer> checkSubscriptionStatus(@Param("subscriberId") Integer subscriberId, @Param("producerId") Integer producerId);
}

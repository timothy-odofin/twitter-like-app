package odofin.oyejide.twitterlikeapp.repository;

import odofin.oyejide.twitterlikeapp.model.entity.Message;
import odofin.oyejide.twitterlikeapp.model.entity.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.List;

public interface MessageRepository extends ReactiveCrudRepository<Message,Integer> {
    @Query(value="select * from T_messages  where uid=:uid")
    Flux<Message> listMessageByProducerId(@Param("uid") Integer uid);
    @Query(value="select * from T_messages  where uid in (:uid)")
    Flux<Message> listMessageByProducerIdIn(@Param("uid") List<Integer> uid);
}

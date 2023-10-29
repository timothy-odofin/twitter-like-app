package odofin.oyejide.twitterlikeapp.repository;

import odofin.oyejide.twitterlikeapp.model.entity.Message;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MessageRepository extends ReactiveCrudRepository<Message,Integer> {
}

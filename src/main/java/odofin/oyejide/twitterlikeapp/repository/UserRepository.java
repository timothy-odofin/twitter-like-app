package odofin.oyejide.twitterlikeapp.repository;

import odofin.oyejide.twitterlikeapp.model.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserRepository extends ReactiveCrudRepository<User,Integer> {
}

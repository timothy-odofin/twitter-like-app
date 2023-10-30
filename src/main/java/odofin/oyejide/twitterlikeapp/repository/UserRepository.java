package odofin.oyejide.twitterlikeapp.repository;

import odofin.oyejide.twitterlikeapp.model.entity.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<User,Integer> {

    @Query(value="select * from T_users  where u_name=:uName and uid =:uid")
    Mono<User> findByUNameAndUid(@Param("uName") String uName, @Param("uid") Integer uid);
    @Query(value="select * from T_users  where u_role=:u_role")
    Flux<User> listUserByRoles(@Param("u_role") String u_role);
    @Query(value="select * from T_users  where u_name=:uName")
    Mono<User> findByUsername(@Param("uName") String uName);
}

package ru.marksblog.risc.interfac;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.marksblog.risc.entity.User;

import java.util.UUID;

@Repository
@Transactional
public interface UserInterface extends ReactiveMongoRepository<User,UUID> {

    @Query("{'nickname' : ?0 }")
    Mono<User> findUserByNickname(@Param("nickname") String nickname);

    @Query("{'nickname' : ?0 },{$set:{'connchannel' : ?1}}")
    Mono<User> updateChannelForUser(@Param("nickname") String nickname, @Param("connchannel") String connchannel);

    @Query("{'connchannel' : ?0 }")
    Flux<User> findAllByConnectedChannel(@Param("connchannel") String connchannel);

}

package ru.marksblog.risc.interfac;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.marksblog.risc.entity.Message;
import ru.marksblog.risc.entity.User;

import java.util.UUID;

@Repository
@Transactional
public interface MessageInterface extends ReactiveCrudRepository<Message,UUID> {

    @Query("{'connectedChannel' : ?0 }")
    Flux<Message> findMessagesByConnectedChannel(@Param("connectedChannel") String connectedChannel);

}

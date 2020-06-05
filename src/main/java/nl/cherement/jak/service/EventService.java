package nl.cherement.jak.service;

import nl.cherement.jak.entity.BoardEntity;
import nl.cherement.jak.entity.CardEntity;
import nl.cherement.jak.entity.EventEntity;
import nl.cherement.jak.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class EventService extends AbstractService<EventEntity> {

    @Autowired
    EventRepository repository;

    public EventService(EventRepository repository) {
        super(repository);
    }

    public List<EventEntity> getByBoard(BoardEntity boardEntity) {
        return repository.findByTo_Board(boardEntity);
    }

    public void createEvent(Authentication authentication, CardEntity updatedEntity, CardEntity currentEntity) {
        EventEntity eventEntity = new EventEntity();
        eventEntity.card = updatedEntity;
        eventEntity.from = currentEntity.column;
        eventEntity.to = updatedEntity.column;
        eventEntity.timestamp = new Timestamp(System.currentTimeMillis());
        save(authentication, eventEntity);
    }

    @Override
    boolean hasAccess(Authentication user, EventEntity entity) {
        return true;
    }
}
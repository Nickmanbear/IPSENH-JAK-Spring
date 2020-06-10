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
        return repository.findByToColumn_BoardOrderByTimestamp(boardEntity);
    }

    public void createEvent(Authentication authentication, CardEntity updatedCardEntity, CardEntity currentCardEntity) {
        EventEntity eventEntity = new EventEntity();
        eventEntity.card = updatedCardEntity;
        eventEntity.fromColumn = currentCardEntity.column;
        eventEntity.toColumn = updatedCardEntity.column;
        eventEntity.timestamp = new Timestamp(System.currentTimeMillis());
        save(authentication, eventEntity);
    }

    @Override
    boolean hasAccess(Authentication user, EventEntity entity) {
        return true;
    }
}
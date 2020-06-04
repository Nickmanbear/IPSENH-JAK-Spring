package nl.cherement.jak.service;

import nl.cherement.jak.entity.EventEntity;
import nl.cherement.jak.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService extends AbstractService<EventEntity> {

    @Autowired
    EventRepository repository;

    public EventService(EventRepository repository) {
        super(repository);
    }

    public List<EventEntity> getByBoardId(Long boardId) {
        return repository.findByTo_BoardId(boardId);
    }

    @Override
    boolean hasAccess(Authentication user, EventEntity entity) {
        return true;
    }
}
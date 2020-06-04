package nl.cherement.jak.service;

import nl.cherement.jak.entity.CardEntity;
import nl.cherement.jak.entity.EventEntity;
import nl.cherement.jak.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class CardService extends AbstractService<CardEntity> {

    @Autowired
    CardRepository repository;

    @Autowired
    EventService eventService;

    public CardService(CardRepository repository) {
        super(repository);
    }

    public List<CardEntity> getByColumnId(Long id) {
        return this.repository.findByColumnId(id);
    }

    @Override
    public CardEntity save(Authentication authentication, CardEntity entity) {
        Optional<CardEntity> cardOptional = repository.findById(entity.id);
        if (cardOptional.isPresent()) {
            CardEntity cardEntity = cardOptional.get();
            if (cardEntity.column.id != entity.column.id) {
                EventEntity eventEntity = new EventEntity();
                eventEntity.card = entity;
                eventEntity.from = cardEntity.column;
                eventEntity.to = entity.column;
                eventEntity.timestamp = new Timestamp(System.currentTimeMillis());
                eventService.save(authentication, eventEntity);
            }
        }
        return super.save(authentication, entity);
    }

    @Override
    boolean hasAccess(Authentication authentication, CardEntity entity) {
        return true;
    }
}
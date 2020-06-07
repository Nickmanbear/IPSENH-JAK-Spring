package nl.cherement.jak.service;

import nl.cherement.jak.entity.CardEntity;
import nl.cherement.jak.entity.EventEntity;
import nl.cherement.jak.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.Principal;
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
    public List<CardEntity> getByBoardId(Long id) {
        return this.repository.findByColumnBoardId(id);
    }

    @Override
    public CardEntity save(Authentication user, CardEntity o) {
        Optional<CardEntity> cardOptional = repository.findById(o.id);
        if (cardOptional.isPresent()) {
            CardEntity cardEntity = cardOptional.get();
            if (cardEntity.column.id != o.column.id) {
                EventEntity eventEntity = new EventEntity();
                eventEntity.card = o;
                eventEntity.fromColumnEntity = cardEntity.column;
                eventEntity.toColumnEntity = o.column;
                eventEntity.timestamp = new Timestamp(System.currentTimeMillis());
                eventService.save(user, eventEntity);
            }
        }
        return super.save(user, o);
    }

    @Override
    boolean hasAccess(Principal user, CardEntity obj) {
        return true;
    }
}
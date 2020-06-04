package nl.cherement.jak.service;

import nl.cherement.jak.entity.CardEntity;
import nl.cherement.jak.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

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
    public CardEntity save(Authentication authentication, CardEntity updatedEntity) {
        Optional<CardEntity> cardOptional = repository.findById(updatedEntity.id);
        if (cardOptional.isPresent()) {
            CardEntity currentEntity = cardOptional.get();
            if (currentEntity.column.id != updatedEntity.column.id) {
                eventService.createEvent(authentication, updatedEntity, currentEntity);
            }
        }
        return super.save(authentication, updatedEntity);
    }

    @Override
    boolean hasAccess(Authentication authentication, CardEntity entity) {
        return true;
    }
}
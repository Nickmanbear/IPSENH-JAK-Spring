package nl.cherement.jak.service;

import java.util.ArrayList;
import java.util.List;

import nl.cherement.jak.entity.CardEntity;
import nl.cherement.jak.exception.RecordNotFoundException;
import nl.cherement.jak.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.cherement.jak.model.CardModel;

@Service
public class CardService {
     
    @Autowired
    CardRepository repository;
     
    public List<CardEntity> all() {
        List<CardEntity> cardEntities = repository.findAll();
         
        if(!cardEntities.isEmpty()) {
            return cardEntities;
        } else {
            return new ArrayList<>();
        }
    }
     
    public CardEntity single(Long id) throws RecordNotFoundException {
        return repository.findById(id)
                .orElseThrow(RecordNotFoundException::new);
    }
     
    public CardEntity update(CardModel cardModel) throws RecordNotFoundException {
        CardEntity cardEntity = new CardEntity();
        cardEntity.importModal(cardModel);

        if (cardEntity.getId() > 0) {
            if (repository.findById(cardModel.getId()).isPresent()) {
                return repository.save(cardEntity);
            } else {
                throw new RecordNotFoundException();
            }
        } else {
            return repository.save(cardEntity);
        }
    }
     
    public void remove(Long id) throws RecordNotFoundException {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException();
        }
    }
}
package nl.cherement.jak.service;

import java.util.ArrayList;
import java.util.List;

import nl.cherement.jak.exception.RecordNotFoundException;
import nl.cherement.jak.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.cherement.jak.model.Card;

@Service
public class CardService {
     
    @Autowired
    CardRepository repository;
     
    public List<Card> all() {
        List<Card> cards = repository.findAll();
         
        if(!cards.isEmpty()) {
            return cards;
        } else {
            return new ArrayList<>();
        }
    }
     
    public Card single(Long id) throws RecordNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException());
    }
     
    public Card update(Card card) throws RecordNotFoundException {
        if (card.getId() > 0) {
            if (repository.findById(card.getId()).isPresent()) {
                return repository.save(card);
            } else {
                throw new RecordNotFoundException();
            }
        } else {
            return repository.save(card);
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
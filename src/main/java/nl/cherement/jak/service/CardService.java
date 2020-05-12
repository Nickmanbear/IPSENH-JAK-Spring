package nl.cherement.jak.service;

import java.util.List;
import java.util.Optional;

import nl.cherement.jak.entity.CardEntity;
import nl.cherement.jak.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    @Autowired
    CardRepository repository;

    public List<CardEntity> all() {
        return repository.findAll();
    }

    public Optional<CardEntity> single(Long id) {
        return repository.findById(id);
    }

    public CardEntity update(CardEntity cardEntity) {
        return repository.save(cardEntity);
    }

    public void remove(Long id) {
        repository.deleteById(id);
    }
}
package nl.cherement.jak.service;

import nl.cherement.jak.entity.CardEntity;
import nl.cherement.jak.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService extends ServiceAbstract<CardEntity>{

    @Autowired
    CardRepository repository;

    public CardService(CardRepository repository) {
        super(repository);
    }
}
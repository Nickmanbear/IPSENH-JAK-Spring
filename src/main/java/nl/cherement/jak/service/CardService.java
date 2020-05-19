package nl.cherement.jak.service;

import nl.cherement.jak.entity.CardEntity;
import nl.cherement.jak.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService extends AbstractService<CardEntity> {

    @Autowired
    CardRepository repository;

    public CardService(CardRepository repository) {
        super(repository);
    }

    public List<CardEntity> getByColumnId(Long id) {
        return this.repository.getByColumnId(id);
    }
}
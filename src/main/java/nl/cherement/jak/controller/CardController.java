package nl.cherement.jak.controller;

import java.util.List;
import java.util.Optional;

import nl.cherement.jak.entity.CardEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.cherement.jak.model.CardModel;
import nl.cherement.jak.service.CardService;
 
@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardService service;

    @GetMapping
    public List<CardEntity> all() {
        return service.all();
    }

    @GetMapping("/{id}")
    public Optional<CardEntity> single(@PathVariable("id") Long id) {
        return service.single(id);
    }

    @PostMapping
    public CardEntity update(CardModel cardModel) {
        CardEntity cardEntity = new CardEntity();
        cardEntity.importModal(cardModel);

        return service.update(cardEntity);
    }

    @DeleteMapping("/{id}")
    public HttpStatus remove(@PathVariable("id") Long id) {
        service.remove(id);

        return HttpStatus.FORBIDDEN;
    }
}
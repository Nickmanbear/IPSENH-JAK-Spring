package nl.cherement.jak.controller;

import nl.cherement.jak.entity.CardEntity;
import nl.cherement.jak.model.CardModel;
import nl.cherement.jak.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
 
@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardService service;

    @GetMapping
    public List<CardEntity> all() {
        return service.findAll();
    }

    @GetMapping("/column/{id}")
    public List<CardEntity> byColumn(@PathVariable("id") Long id) {
        return service.getByColumnId(id);
    }

    @GetMapping("/{id}")
    public Optional<CardEntity> single(@PathVariable("id") Long id) {
        return service.get(id);
    }

    @PostMapping
    public CardEntity update(CardModel cardModel) {
        CardEntity cardEntity = new CardEntity();
        cardEntity.importModal(cardModel);

        return service.update(cardEntity);
    }

    @DeleteMapping("/{id}")
    public HttpStatus remove(@PathVariable("id") Long id) {
        service.deleteById(id);

        return HttpStatus.FORBIDDEN;
    }
}
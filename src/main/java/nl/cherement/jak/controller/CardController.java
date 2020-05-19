package nl.cherement.jak.controller;

import java.util.List;
import java.util.Optional;

import nl.cherement.jak.entity.CardEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import nl.cherement.jak.model.CardModel;
import nl.cherement.jak.service.CardService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardService service;

    @GetMapping
    public List<CardEntity> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<CardEntity> findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PostMapping
    public CardEntity save(@RequestBody CardModel cardModel) {
        CardEntity cardEntity = new CardEntity();
        cardEntity.importModal(cardModel);

        return service.save(cardEntity);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteById(@PathVariable("id") Long id) {
        service.deleteById(id);

        return HttpStatus.OK;
    }
}
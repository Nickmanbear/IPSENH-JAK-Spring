package nl.cherement.jak.controller;

import java.util.List;

import nl.cherement.jak.entity.CardEntity;
import nl.cherement.jak.exception.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<CardEntity>> all() {
        List<CardEntity> list = service.all();
 
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<CardEntity> single(@PathVariable("id") Long id) throws RecordNotFoundException {
        CardEntity card = service.single(id);
 
        return new ResponseEntity<>(card, new HttpHeaders(), HttpStatus.OK);
    }
 
    @PostMapping
    public ResponseEntity<CardEntity> update(CardModel card) throws RecordNotFoundException {
        CardEntity updatedCard = service.update(card);

        return new ResponseEntity<>(updatedCard, new HttpHeaders(), HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    public HttpStatus remove(@PathVariable("id") Long id) throws RecordNotFoundException {
        service.remove(id);

        return HttpStatus.FORBIDDEN;
    }
}
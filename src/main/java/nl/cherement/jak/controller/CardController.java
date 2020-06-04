package nl.cherement.jak.controller;

import nl.cherement.jak.entity.CardEntity;
import nl.cherement.jak.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardService service;

    @GetMapping
    public List<CardEntity> findAll(Authentication authentication) {
        return service.findAll(authentication);
    }

    @GetMapping("/column/{id}")
    public List<CardEntity> byColumn(@PathVariable("id") Long id) {
        return service.getByColumnId(id);
    }

    @GetMapping("/{id}")
    public Optional<CardEntity> findById(Authentication authentication, @PathVariable("id") Long id) {
        return service.findById(authentication,id);
    }

    @PostMapping
    public CardEntity save(Authentication authentication, @RequestBody CardDTO cardDTO) {
        return service.save(authentication, cardDTO.toEntity());
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteById(Authentication authentication, @PathVariable("id") Long id) {
        service.deleteById(authentication,id);

        return HttpStatus.OK;
    }
}

class CardDTO extends CardEntity {

    CardEntity toEntity() {
        CardEntity cardEntity = new CardEntity();
        cardEntity.id = id;
        cardEntity.column = column;
        cardEntity.assignedUser = assignedUser;
        cardEntity.name = name;
        cardEntity.description = description;
        cardEntity.priority = priority;
        cardEntity.points = points;

        return cardEntity;
    }
}
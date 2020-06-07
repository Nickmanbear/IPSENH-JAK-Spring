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
    public List<CardEntity> findByColumnId(@PathVariable Long id) {
        return service.getByColumnId(id);
    }
    @GetMapping("/board/{id}")
    public List<CardEntity> findAllByBoardId(@PathVariable Long id) {
        return service.getByBoardId(id);
    }

    @GetMapping("/{id}")
    public Optional<CardEntity> findById(Authentication authentication, @PathVariable Long id) {
        return service.findById(authentication, id);
    }

    @PostMapping
    public CardEntity save(Authentication authentication, @RequestBody CardDTO cardDTO) {
        return service.save(authentication, cardDTO.toEntity());
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(Authentication authentication, @PathVariable("id") CardEntity cardEntity) {
        service.delete(authentication, cardEntity);

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
package nl.cherement.jak.controller;

import nl.cherement.jak.entity.EventEntity;
import nl.cherement.jak.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/event")
@MessageMapping("/event")
public class EventController {

    @Autowired
    EventService service;

    @GetMapping("")
    public List<EventEntity> findByColumn(Authentication authentication, @PathVariable("columnid") Long columnid) {
        return service.getByColumnId(columnid);
    }
}

package nl.cherement.jak.controller;

import nl.cherement.jak.entity.ColumnEntity;
import nl.cherement.jak.service.ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/column")
public class ColumnController {

    @Autowired
    ColumnService service;

    @GetMapping
    public List<ColumnEntity> findAll(Authentication authentication) {
        return service.findAll(authentication);
    }

    @GetMapping("/board/{id}")
    public List<ColumnEntity> findByBoardId(@PathVariable Long id) {
        return service.getByBoardId(id);
    }
    @GetMapping("/board/{id}/last")
    public List<ColumnEntity> findLastColumnByBoardId(@PathVariable Long id) {
        return service.getLastColumnByBoardId(id);
    }

    @GetMapping("/{id}")
    public Optional<ColumnEntity> findById(Authentication authentication, @PathVariable Long id) {
        return service.findById(authentication, id);
    }

    @PostMapping
    public ColumnEntity save(Authentication authentication, @RequestBody ColumnDTO columnDTO) {
        return service.save(authentication, columnDTO.toEntity());
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(Authentication authentication, @PathVariable("id") ColumnEntity columnEntity) {
        service.delete(authentication, columnEntity);

        return HttpStatus.OK;
    }
}

class ColumnDTO extends ColumnEntity {

    ColumnEntity toEntity() {
        ColumnEntity columnEntity = new ColumnEntity();
        columnEntity.id = id;
        columnEntity.board = board;
        columnEntity.name = name;

        return columnEntity;
    }
}
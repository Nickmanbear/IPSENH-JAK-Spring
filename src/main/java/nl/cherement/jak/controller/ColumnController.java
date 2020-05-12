package nl.cherement.jak.controller;

import nl.cherement.jak.entity.ColumnEntity;
import nl.cherement.jak.model.ColumnModel;
import nl.cherement.jak.service.ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/column")
public class ColumnController {

    @Autowired
    ColumnService service;

    @GetMapping
    public List<ColumnEntity> all() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<ColumnEntity> single(@PathVariable("id") Long id) {
        return service.get(id);
    }

    @PostMapping
    public ColumnEntity update(ColumnModel columnModel) {
        ColumnEntity columnEntity = new ColumnEntity();
        columnEntity.importModal(columnModel);

        return service.update(columnEntity);
    }

    @DeleteMapping("/{id}")
    public HttpStatus remove(@PathVariable("id") Long id) {
        service.deleteById(id);

        return HttpStatus.FORBIDDEN;
    }
}
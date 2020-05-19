package nl.cherement.jak.controller;

import nl.cherement.jak.entity.ColumnEntity;
import nl.cherement.jak.model.ColumnModel;
import nl.cherement.jak.service.ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public List<ColumnEntity> findAll() {
        return service.findAll();
    }

    @GetMapping("/board/{id}")
    public List<ColumnEntity> byBoard(@PathVariable("id") Long id) {
        return service.getByBoardId(id);
    }

    @GetMapping("/{id}")
    public Optional<ColumnEntity> findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ColumnEntity save(@RequestBody ColumnModel columnModel) {
        ColumnEntity columnEntity = new ColumnEntity();
        columnEntity.importModal(columnModel);

        return service.save(columnEntity);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteById(@PathVariable("id") Long id) {
        service.deleteById(id);

        return HttpStatus.OK;
    }
}
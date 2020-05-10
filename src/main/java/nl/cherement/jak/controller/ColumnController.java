package nl.cherement.jak.controller;

import nl.cherement.jak.entity.ColumnEntity;
import nl.cherement.jak.exception.RecordNotFoundException;
import nl.cherement.jak.model.ColumnModel;
import nl.cherement.jak.service.ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
public class ColumnController {
    @Autowired
    ColumnService service;
 
    @GetMapping
    public ResponseEntity<List<ColumnEntity>> all() {
        List<ColumnEntity> list = service.all();
 
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<ColumnEntity> single(@PathVariable("id") Long id) throws RecordNotFoundException {
        ColumnEntity column = service.single(id);
 
        return new ResponseEntity<>(column, new HttpHeaders(), HttpStatus.OK);
    }
 
    @PostMapping
    public ResponseEntity<ColumnEntity> update(ColumnModel column) throws RecordNotFoundException {
        ColumnEntity updatedColumn = service.update(column);

        return new ResponseEntity<>(updatedColumn, new HttpHeaders(), HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    public HttpStatus remove(@PathVariable("id") Long id) throws RecordNotFoundException {
        service.remove(id);

        return HttpStatus.FORBIDDEN;
    }
}
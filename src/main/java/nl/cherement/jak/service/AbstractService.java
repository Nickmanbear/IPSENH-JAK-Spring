package nl.cherement.jak.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class AbstractService<T> {

    private JpaRepository<T, Long> repository;

    public AbstractService(JpaRepository<T, Long> repository) {
        this.repository = repository;
    }

    public T save(T o) {
        return this.repository.save(o);
    }

    public void delete(T o) {
        this.repository.delete(o);
    }

    public void deleteById(Long o) {
        this.repository.deleteById(o);
    }


    public List<T> findAll() {
        return this.repository.findAll();
    }

    public Optional<T> findById(Long id) {
        return this.repository.findById(id);
    }


}

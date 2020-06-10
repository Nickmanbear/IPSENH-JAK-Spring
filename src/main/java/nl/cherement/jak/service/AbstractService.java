package nl.cherement.jak.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractService<T> {

    private final JpaRepository<T, Long> repository;

    public AbstractService(JpaRepository<T, Long> repository) {
        this.repository = repository;
    }

    abstract boolean hasAccess(Authentication authentication, T entity);

    public T save(Authentication authentication, T entity) {
        if (!hasAccess(authentication, entity)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, authentication.getName());
        }
        return this.repository.save(entity);
    }

    public void delete(Authentication authentication, T entity) {
        if (!hasAccess(authentication, entity)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, authentication.getName());
        }
        this.repository.delete(entity);
    }

    public List<T> findAll(Authentication authentication) {
        List<T> entities = this.repository.findAll();
        return entities
                .stream()
                .filter(entity -> this.hasAccess(authentication, entity))
                .collect(Collectors.toList());
    }

    public Optional<T> findById(Authentication authentication, Long id) {
        Optional<T> entity = this.repository.findById(id);
        if (!entity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if (!hasAccess(authentication, entity.get())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "You do not have access to the object with id " + id);
        }
        return entity;
    }
}

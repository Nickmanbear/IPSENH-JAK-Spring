package nl.cherement.jak.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractService<T> {

    private final JpaRepository<T, Long> repository;

    public AbstractService(JpaRepository<T, Long> repository) {
        this.repository = repository;
    }

    abstract boolean hasAccess(Principal user, T obj);

    public T save(Authentication user, T o) {
        if (!hasAccess(user, o)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, user.getName());
        }
        return this.repository.save(o);
    }

    public void delete(Authentication user, T o) {
        if (!hasAccess(user, o)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, user.getName());
        }
        this.repository.delete(o);
    }

    public void deleteById(Authentication user, Long o) {
        Optional<T> obj = this.repository.findById(o);

        if (!obj.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        if (!hasAccess(user, obj.get())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, user.getName() + " Cannot delete this");

        }
        this.repository.deleteById(o);
    }

    public List<T> findAll(Authentication user) {
        List<T> objs = this.repository.findAll();
        return objs
                .stream()
                .filter(o -> this.hasAccess(user, o))
                .collect(Collectors.toList())
                ;
    }

    public Optional<T> findById(Authentication user, Long id) {

        Optional<T> obj = this.repository.findById(id);
        if (!obj.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        if (!hasAccess(user, obj.get())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unable to find " + id.toString());
        }
        return obj;

    }
}

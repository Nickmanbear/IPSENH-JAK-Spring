package nl.cherement.jak.service;

import nl.cherement.jak.entity.BoardEntity;
import nl.cherement.jak.entity.UserEntity;
import nl.cherement.jak.repository.BoardRepository;
import nl.cherement.jak.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BoardService extends AbstractService<BoardEntity> {

    @Autowired
    BoardRepository repository;

    @Autowired
    UserRespository userRespository;

    public BoardService(BoardRepository repository) {
        super(repository);
    }

    public List<BoardEntity> findByUser(Authentication authentication) {
        UserEntity userEntity= userRespository.findByUsername((String) authentication.getPrincipal());
        return repository.findByUsers(userEntity);
    }

}
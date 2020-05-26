package nl.cherement.jak.service;

import nl.cherement.jak.entity.BoardEntity;
import nl.cherement.jak.repository.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class BoardServiceTests {

    private List<BoardEntity> boards;
    private BoardEntity board;
    private BoardEntity board2;


    @Autowired
    private BoardService service;

    @MockBean
    private BoardRepository repository;

    @BeforeEach
    public void initialize() {
        board = new BoardEntity();
        board2 = new BoardEntity();
        boards = new ArrayList<BoardEntity>();

        board.setId(1l);
        board2.setId(1l);
        boards.add(board);
        boards.add(board2);

        doReturn(boards).when(repository).findByUsers_Username(any());

        doReturn(boards).when(repository).findAll();

        doReturn(Optional.of(board)).when(repository).findById(any(Long.class));

        doReturn(board).when(repository).save(any(BoardEntity.class));

        doNothing().when(repository).deleteById(any());
    }


    @Test
    void findByUserName() {

        assertSame(boards, service.findByUserName("admin"));
    }

    @Test
    void findAll() {

        assertSame(boards, service.findAll());
    }

    @Test
    void findById() {
        assertSame(board, service.findById(1l).get());
    }


    @Test
    void save(){
        assertSame(board, service.save(board));
    }

    @Test
    void delete(){

        service.delete(board);
    }

    @Test
    void deleteById(){

        service.deleteById(1l);

        verify(repository, times(1)).deleteById(any());

    }
}
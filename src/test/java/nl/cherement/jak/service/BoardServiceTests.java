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

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

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
    public void initialize(){
        board = new BoardEntity();
        board2 = new BoardEntity();
        boards=  new ArrayList<BoardEntity>();

        board.setId(1l);
        board2.setId(1l);
        boards.add(board);
        boards.add(board2);

        doReturn(boards).when(repository).findByUsers_Username(any());
    }


    @Test
    void findByUserName() {

        assertSame(boards, service.findByUserName("admin"));
    }
}
package nl.cherement.jak.service;

import nl.cherement.jak.entity.BoardEntity;
import nl.cherement.jak.entity.UserEntity;
import nl.cherement.jak.repository.BoardRepository;
import nl.cherement.jak.repository.UserRespository;
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
    private UserEntity user;


    @Autowired
    private BoardService service;

    @MockBean
    private BoardRepository boardRepository;

    @MockBean
    private UserRespository userRespository;

    BoardServiceTests() {
    }

    @BeforeEach
    public void initialize() {
        board = new BoardEntity();
        board2 = new BoardEntity();
        boards = new ArrayList<BoardEntity>();
        user = new UserEntity();

        board.setId(1l);
        board.setUsers(new ArrayList<>());
        board2.setId(1l);
        boards.add(board);
        boards.add(board2);

        user.setId(1);
        user.setUsername("test user");
        user.setPassword("password");
        user.setPermissions("admin");
        user.setRoles("ROLE_ADMIN");

        doReturn(boards).when(boardRepository).findByUsers_Username(any());

        doReturn(boards).when(boardRepository).findAll();

        doReturn(Optional.of(board)).when(boardRepository).findById(any(Long.class));

        doReturn(board).when(boardRepository).save(any(BoardEntity.class));

        doNothing().when(boardRepository).deleteById(any());

        doReturn(board).when(boardRepository).getOne(1L);

        doReturn(user).when(userRespository).getOne(1L);
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
        verify(boardRepository, times(1)).delete(any());

    }

    @Test
    void deleteById(){

        service.deleteById(1l);

        verify(boardRepository, times(1)).deleteById(any());

    }

    @Test
    void addUser() {

        List<UserEntity> tempList = board.getUsers();
        tempList.add(user);
        board.setUsers(tempList);

        assertSame(board, service.addUser(1L, 1L));
    }
}
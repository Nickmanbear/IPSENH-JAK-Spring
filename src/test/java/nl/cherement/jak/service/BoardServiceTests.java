package nl.cherement.jak.service;

import nl.cherement.jak.entity.BoardEntity;
import nl.cherement.jak.entity.UserEntity;
import nl.cherement.jak.repository.BoardRepository;
import nl.cherement.jak.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class BoardServiceTests {

    private List<BoardEntity> boards;
    private BoardEntity board;
    private UserEntity user;


    @Autowired
    private BoardService service;

    @MockBean
    private BoardRepository boardRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private Authentication authentication;

    BoardServiceTests() {
    }

    @BeforeEach
    public void initialize() {
        board = new BoardEntity();
        board.users = new ArrayList<UserEntity>();
        boards = new ArrayList<BoardEntity>();
        user = new UserEntity();

        user.id = 1;
        user.username = "alex";
        user.password = "password";
        user.permissions = "admin";
        user.roles = "ROLE_ADMIN";
        user.active = true;

        board.id = 1L;

        board.users.add(user);
        boards.add(board);



        doReturn(boards).when(boardRepository).findByUsers_Username(any());
        doReturn(boards).when(boardRepository).findAll();
        doReturn(Optional.of(board)).when(boardRepository).findById(any(Long.class));
        doReturn(board).when(boardRepository).save(any(BoardEntity.class));
        doNothing().when(boardRepository).deleteById(any());
        doReturn(board).when(boardRepository).getOne(1L);
        doReturn(user).when(userRepository).getOne(1L);
        doReturn("alex").when(authentication).getName();
    }


    @Test
    void findByUserName() {
        assertSame(boards, service.findByUserName("admin"));
    }

    @Test
    void findAll() {
        assertEquals(boards, service.findAll(authentication));
    }

    @Test
    void findById() {
        assertSame(board, service.findById(authentication,1L).get());
    }


    @Test
    void save(){
        assertSame(board, service.save(authentication,board));
    }

    @Test
    void delete(){
        service.delete(authentication,board);

        verify(boardRepository, times(1)).delete(any());
    }

    @Test
    void deleteById(){
        service.deleteById(authentication,1L);

        verify(boardRepository, times(1)).deleteById(any());
    }

    @Test
    void addUser() {
        UserEntity user2 = new UserEntity();
        user2.id = 2;
        board.users.add(user2);

        assertEquals(board, service.addUser(authentication, 1L, user2));
    }
}

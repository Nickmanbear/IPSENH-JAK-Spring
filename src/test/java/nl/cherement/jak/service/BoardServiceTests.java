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
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class BoardServiceTests {

    private List<BoardEntity> boards;
    private BoardEntity board;
    private UserEntity user;
    private UserEntity user2;
    private BoardEntity boardWithoutUsers;


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
        boardWithoutUsers = new BoardEntity();
        boardWithoutUsers.users = new ArrayList<UserEntity>();
        user = new UserEntity();
        user2 = new UserEntity();

        user2.id= 2;
        user2.username= "alex2";

        user.id = 1;
        user.username = "alex";
        user.password = "password";
        user.permissions = "admin";
        user.roles = "ROLE_ADMIN";
        user.active = true;

        board.id = 1L;
        boardWithoutUsers.id = 2L;

        boardWithoutUsers.users.add(user2);
        board.users.add(user);
        boards.add(board);

        doReturn(boards).when(boardRepository).findByUsers_Username(any());
        doReturn(boards).when(boardRepository).findAll();
        doReturn(Optional.of(user)).when(userRepository).findById(any(Long.class));
        doReturn(Optional.of(boardWithoutUsers)).when(boardRepository).findById(3L);
        doReturn(Optional.empty()).when(boardRepository).findById(2L);
        doReturn(Optional.of(board)).when(boardRepository).findById(1L);
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
        assertSame(board, service.findById(authentication, 1L).get());
    }


    @Test
    void save() {
        assertSame(board, service.save(authentication, board));
    }

    @Test
    void delete() {
        service.delete(authentication, board);

        verify(boardRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(authentication, 1L);

        verify(boardRepository, times(1)).deleteById(any());
    }

    @Test
    void addUser() {
        UserEntity user2 = new UserEntity();
        user2.id = 2;
        board.users.add(user2);

        assertEquals(board, service.addUser(authentication, 1L, user2));
    }

    @Test
    void addUser_User_is_Null() {
        assertThrows(ResponseStatusException.class, () ->
                service.addUser(authentication, 1L, null));
    }

    @Test
    void findById_object_is_not_present() {

        assertThrows(ResponseStatusException.class, () ->
                service.findById(authentication, 2L));
    }

    @Test
    void deleteById_object_is_not_present() {
        assertThrows(ResponseStatusException.class, () ->
                service.deleteById(authentication, 2L));
    }

    @Test
    void addUser_object_is_not_present() {
        assertThrows(ResponseStatusException.class, () ->
                service.addUser(authentication, 2L, user));
    }

    @Test
    void save_no_acces() {
        assertThrows(ResponseStatusException.class, () ->
                service.save(authentication, boardWithoutUsers));

    }

    @Test
    void delete_no_acces() {
        assertThrows(ResponseStatusException.class, () ->
                service.delete(authentication, boardWithoutUsers));

    }

    @Test
    void deleteById_no_acces() {
        assertThrows(ResponseStatusException.class, () ->
                service.deleteById(authentication, 3L));

    }
    @Test
    void findById_no_acces() {
        assertThrows(ResponseStatusException.class, () ->
                service.findById(authentication, 3L));

    }

}

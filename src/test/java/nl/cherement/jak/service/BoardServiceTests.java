package nl.cherement.jak.service;

import nl.cherement.jak.Application;
import nl.cherement.jak.entity.BoardEntity;
import nl.cherement.jak.entity.TeamEntity;
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

@SpringBootTest(classes = Application.class)
class BoardServiceTests {

    private List<BoardEntity> boards;
    private BoardEntity board;
    private BoardEntity boardWithoutUsers;
    private TeamEntity team;


    @Autowired
    private BoardService boardService;

    @MockBean
    private TeamService teamService;

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
        team = new TeamEntity();
        UserEntity user = new UserEntity();
        UserEntity user2 = new UserEntity();

        board.id = 1L;
        boardWithoutUsers.id = 2L;

        boardWithoutUsers.users.add(user2);
        board.users.add(user);
        boards.add(board);
        user.id = 1;
        user.username = "alex";
        user.password = "password";
        user.permissions = "admin";
        user.roles = "ROLE_ADMIN";
        user.active = true;

        user2.id= 2;
        user2.username= "alex2";

        boardWithoutUsers.users.add(user2);
        board.users.add(user);
        boards.add(board);

        team.id = 1;
        team.name = "team";

        doReturn(boards).when(boardRepository).findByUsers_Username(any());
        doReturn(boards).when(boardRepository).findAll();
        doReturn(Optional.of(user)).when(userRepository).findById(any(Long.class));
        doReturn(Optional.of(team)).when(teamService).findById(any(Authentication.class), any());
        doReturn(boards).when(boardRepository).findByTeam(any());
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
        assertSame(boards, boardService.findByUserName("admin"));
    }

    @Test
    void findByTeam() {
        assertSame(boards, boardService.findByTeam(authentication, 1L));
    }

    @Test
    void findAll() {
        assertEquals(boards, boardService.findAll(authentication));
    }

    @Test
    void findById() {
        assertSame(board, boardService.findById(authentication, 1L).get());
    }

    @Test
    void save() {
        assertSame(board, boardService.save(authentication, board));
    }

    @Test
    void delete() {
        boardService.delete(authentication, board);

        verify(boardRepository, times(1)).delete(any());
    }

    @Test
    void addUser() {
        UserEntity user2 = new UserEntity();
        user2.id = 2;
        board.users.add(user2);

        assertEquals(board, boardService.addUser(authentication, board, user2));
    }

    @Test
    void addTeam() {
        assertSame(board, boardService.addTeam(authentication, 1L, team));
    }

    @Test
    void deleteUser() {
        assertSame(board, boardService.deleteUser(authentication, 1L, 1L));
    }

    @Test
    void deleteTeam() {
        assertSame(board, boardService.deleteTeam(1L));
    }

    @Test
    void findById_object_is_not_present() {
        assertThrows(ResponseStatusException.class, () ->
                boardService.findById(authentication, 2L));
    }

    @Test
    void save_no_access() {
        assertThrows(ResponseStatusException.class, () ->
                boardService.save(authentication, boardWithoutUsers));
    }

    @Test
    void delete_no_access() {
        assertThrows(ResponseStatusException.class, () ->
                boardService.delete(authentication, boardWithoutUsers));
    }

    @Test
    void findById_no_access() {
        assertThrows(ResponseStatusException.class, () ->
                boardService.findById(authentication, 3L));
    }

}

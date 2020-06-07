package nl.cherement.jak.controller;

import nl.cherement.jak.Application;
import nl.cherement.jak.entity.BoardEntity;
import nl.cherement.jak.entity.EventEntity;
import nl.cherement.jak.entity.TeamEntity;
import nl.cherement.jak.entity.UserEntity;
import nl.cherement.jak.service.BoardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class BoardControllerTests {

    private final BoardDTO boardDTO = new BoardDTO();
    private final BoardEntity board = new BoardEntity();
    private final BoardEntity board2 = new BoardEntity();
    private List<BoardEntity> boards;

    private final List<UserEntity> userEntities = new ArrayList<UserEntity>();

    private List<EventEntity> events;

    private TeamEntity team = new TeamEntity();

    @Autowired
    private BoardController controller;

    @MockBean
    private BoardService service;

    @MockBean
    private Principal principal;

    @MockBean
    private Authentication authentication;

    @BeforeEach
    public void initialize() {
        board.id = 1;
        board2.id = 2;
        boards = new ArrayList<BoardEntity>();
        boards.add(board);
        boards.add(board2);

        EventEntity event = new EventEntity();
        EventEntity event2 = new EventEntity();
        event.id = 1L;
        event2.id = 2L;
        events = new ArrayList<EventEntity>();
        events.add(event);
        events.add(event2);

        team.id = 1;
        team.name = "team";

//TODO RECHECK THIS MESS
        doReturn(Optional.of(board)).when(service).findById(any(Authentication.class), any(Long.class));
        doReturn(boards).when(service).findByUserName(any());
        doNothing().when(service).delete(any(Authentication.class), any(BoardEntity.class));
        doReturn(board).when(service).save(any(Authentication.class), any(BoardEntity.class));
        doReturn(board).when(service).addUser(any(Authentication.class), any(BoardEntity.class), any(UserEntity.class));
        doReturn(boards).when(service).findAll(any(Authentication.class));
        doReturn("alex").when(authentication).getName();
        doReturn(board).when(service).addTeam(any(Authentication.class), any(), any());
        doReturn(board).when(service).deleteUser(any(Authentication.class), any(), any());
        doReturn(board).when(service).deleteTeam(any());
        doReturn(events).when(service).getTimeline(any(Authentication.class), any(BoardEntity.class));
    }


    @Test
    void DTO() {
        UserEntity userEntity = new UserEntity();
        userEntity.id = 1;
        userEntities.add(userEntity);
        boardDTO.users = userEntities;
        boardDTO.id = 1;
        boardDTO.name = "TestBoard";

        assertEquals("BoardEntity [id=" + boardDTO.id + ", users=" + boardDTO.users
                + ", name=" + boardDTO.name + ", team=" + boardDTO.team + "]", boardDTO.toEntity().toString());
    }

    @Test
    void findAll() {
        assertSame(boards, controller.findAll(authentication));
    }

    @Test
    void findById() {
        assertSame(board, controller.findById(authentication, 1L).get());
    }

    @Test
    void save() {
        boardDTO.id = 1L;
        assertSame(board, controller.save(authentication, boardDTO));
    }

    @Test
    void delete() {
        assertSame(HttpStatus.OK, controller.delete(authentication, board));
    }

    @Test
    void cardMoved() {
        assertTrue(controller.cardMoved());
    }

    @Test
    void addUser() {
        assertSame(board, controller.addUser(authentication, board, new UserEntity()));
    }

    @Test
    void getTimeline() {
        assertSame(events, controller.getTimeline(authentication, new BoardEntity()));

    }

    @Test
    void addTeam() {
        assertSame(board, controller.addTeam(authentication, 1L, team));
    }

    @Test
    void deleteUser() {
        assertSame(board, controller.deleteUser(authentication, 1L, 1L));
    }

    @Test
    void deleteTeam() {
        assertSame(board, controller.deleteTeam(1L));
    }
}

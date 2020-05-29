package nl.cherement.jak.controller;

import nl.cherement.jak.entity.BoardEntity;
import nl.cherement.jak.entity.UserEntity;
import nl.cherement.jak.service.BoardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

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
    private final BoardEntity board= new BoardEntity();
    private final BoardEntity board2 = new BoardEntity();
    private List<BoardEntity> boards;

    private final List<UserEntity> userEntities = new ArrayList<UserEntity>();

    @Autowired
    private BoardController controller;

    @MockBean
    private BoardService service;

    @MockBean
    private Principal principal;

    @BeforeEach
    public void initialize(){
        board.id = 1;
        board2.id = 2;
        boards = new ArrayList<BoardEntity>();
        boards.add(board);
        boards.add(board2);

        doReturn(Optional.of(board)).when(service).findById(1L);
        doReturn("test").when(principal).getName();
        doReturn(boards).when(service).findBoardByUserName("test");
        doNothing().when(service).deleteById(1L);
        doReturn(board).when(service).save(any(BoardEntity.class));
        doReturn(board).when(service).addUser(1L, 1L);
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
                + ", name=" + boardDTO.name + "]", boardDTO.toEntity().toString());
    }

    @Test
    void findById() {
        assertSame(board, controller.findById(1L).get());
    }

    @Test
    void findAll() {
        assertSame(boards, controller.findAll(principal));
    }

    @Test
    void save() {
        boardDTO.id = 1L;
        assertSame(board, controller.save(boardDTO));
    }

    @Test
    void deleteById() {
        assertSame(HttpStatus.OK, controller.deleteById(1L));
    }

    @Test
    void cardMoved() {
      assertTrue(controller.cardMoved());
    }

    @Test
    void addUser() {
        assertSame(board, controller.addUser(1L, 1L));
    }
}

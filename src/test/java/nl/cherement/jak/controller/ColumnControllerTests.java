package nl.cherement.jak.controller;

import nl.cherement.jak.Application;
import nl.cherement.jak.entity.BoardEntity;
import nl.cherement.jak.entity.ColumnEntity;
import nl.cherement.jak.service.ColumnService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class ColumnControllerTests {

    private final ColumnDTO columnDTO = new ColumnDTO();
    private final ColumnEntity column = new ColumnEntity();
    private final ColumnEntity column2 = new ColumnEntity();
    private List<ColumnEntity> columns;

    @Autowired
    private ColumnController controller;

    @MockBean
    private ColumnService service;

    @MockBean
    private Authentication authentication;

    @BeforeEach
    public void initialize(){
        columnDTO.id = 1L;
        column.id = 1L;
        column2.id = 2L;
        columns = new ArrayList<>();
        columns.add(column);
        columns.add(column2);

        doReturn(columns).when(service).findAll(any(Authentication.class));
        doReturn(columns).when(service).getByBoardId(any());
        doReturn(Optional.of(column)).when(service).findById(any(Authentication.class),any());
        doReturn(column).when(service).save(any(Authentication.class),any(ColumnEntity.class));
        doNothing().when(service).delete(any(Authentication.class),any(ColumnEntity.class));
        doReturn("alex").when(authentication).getName();
    }

    @Test
    void DTO() {
        columnDTO.id = 1L;
        columnDTO.board = new BoardEntity();
        columnDTO.name = "TestColumn";

        assertEquals("ColumnEntity [id=" + columnDTO.id + ", board=" + columnDTO.board
                + ", name=" + columnDTO.name + "]", columnDTO.toEntity().toString());
    }

    @Test
    void findAll() {
        assertSame(columns, controller.findAll(authentication));
    }

    @Test
    void byBoard() {
        assertSame(columns, controller.findByBoardId(1L));
    }

    @Test
    void findById() {
        assertSame(column, controller.findById(authentication,1L).get());
    }

    @Test
    void save() {
        assertSame(column, controller.save(authentication,columnDTO));
    }

    @Test
    void deleteById() {
        assertSame(HttpStatus.OK, controller.delete(authentication,column));
    }
}

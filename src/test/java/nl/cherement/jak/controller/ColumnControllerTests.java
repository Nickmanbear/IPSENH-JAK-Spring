package nl.cherement.jak.controller;

import nl.cherement.jak.entity.BoardEntity;
import nl.cherement.jak.entity.ColumnEntity;
import nl.cherement.jak.service.ColumnService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

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

    @BeforeEach
    public void initialize(){
        columnDTO.id = 1;
        column.id = 1;
        column2.id = 2;
        columns = new ArrayList<>();
        columns.add(column);
        columns.add(column2);

        doReturn(columns).when(service).findAll();
        doReturn(columns).when(service).getByBoardId(any());
        doReturn(Optional.of(column)).when(service).findById(any());
        doReturn(column).when(service).save(any(ColumnEntity.class));
        doNothing().when(service).deleteById(any());
    }

    @Test
    void DTO() {
        columnDTO.id = 1;
        columnDTO.board = new BoardEntity();
        columnDTO.name = "TestColumn";

        assertEquals("ColumnEntity [id=" + columnDTO.id + ", board=" + columnDTO.board
                + ", name=" + columnDTO.name + "]", columnDTO.toEntity().toString());
    }

    @Test
    void findAll() {
        assertSame(columns, controller.findAll());
    }

    @Test
    void byBoard() {
        assertSame(columns, controller.byBoard(1L));
    }

    @Test
    void findById() {
        assertSame(column, controller.findById(1L).get());
    }

    @Test
    void save() {
        assertSame(column, controller.save(columnDTO));
    }

    @Test
    void deleteById() {
        assertSame(HttpStatus.OK, controller.deleteById(1L));
    }
}

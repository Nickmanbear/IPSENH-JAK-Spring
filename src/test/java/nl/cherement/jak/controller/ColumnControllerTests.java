package nl.cherement.jak.controller;

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
    private ColumnEntity column= new ColumnEntity();
    private ColumnEntity column2 = new ColumnEntity();
    private List<ColumnEntity> columns;

    @Autowired
    private ColumnController controller;

    @MockBean
    private ColumnService service;

    @BeforeEach
    public void initialize(){
        columnDTO.setId(1);
        column.setId(1);
        column2.setId(2);
        columns = new ArrayList<ColumnEntity>();
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
        columnDTO.setId(1);
        columnDTO.setBoardId(1);
        columnDTO.setName("TestColumn");

        assertEquals("ColumnEntity [id=" + columnDTO.getId() + ", boardId=" + columnDTO.getBoardId()
                + ", name=" + columnDTO.getName() + "]", columnDTO.toEntity().toString());
    }

    @Test
    void findAll() {

        assertSame(columns, controller.findAll());
    }

    @Test
    void byBoard() {

        assertSame(columns, controller.byBoard(1l));

    }

    @Test
    void findById() {

        assertSame(column, controller.findById(1l).get());
    }

    @Test
    void save() {

        assertSame(column, controller.save(columnDTO));
    }

    @Test
    void deleteById() {

        assertSame(HttpStatus.OK, controller.deleteById(1l));
    }
}

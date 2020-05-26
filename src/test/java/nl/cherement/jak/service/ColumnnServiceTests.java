package nl.cherement.jak.service;

import nl.cherement.jak.entity.ColumnEntity;
import nl.cherement.jak.repository.ColumnRepository;
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
public class ColumnnServiceTests {

    private List<ColumnEntity> columns;
    private ColumnEntity column;
    private ColumnEntity column2;


    @Autowired
    private ColumnService service;

    @MockBean
    private ColumnRepository repository;

    @BeforeEach
    public void initialize(){
        column = new ColumnEntity();
        column2 = new ColumnEntity();
        columns =  new ArrayList<ColumnEntity>();

        column.setId(1l);
        column2.setId(1l);
        columns.add(column);
        columns.add(column2);

        doReturn(columns).when(repository).getByBoardId(any(Long.class));



    }

    @Test
    void getByBoardId() {

        assertSame(columns, service.getByBoardId(1l));
    }
}

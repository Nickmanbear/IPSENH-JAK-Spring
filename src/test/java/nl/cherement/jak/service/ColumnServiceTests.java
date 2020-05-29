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
class ColumnServiceTests {

    private List<ColumnEntity> columns;

    @Autowired
    private ColumnService service;

    @MockBean
    private ColumnRepository repository;

    @BeforeEach
    public void initialize() {
        ColumnEntity column = new ColumnEntity();
        columns = new ArrayList<>();
        column.id = 1;
        columns.add(column);

        doReturn(columns).when(repository).findByBoardId(any(Long.class));
    }

    @Test
    void getByBoardId() {
        assertSame(columns, service.getByBoardId(1L));
    }
}

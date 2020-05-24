package nl.cherement.jak.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ColumnEntityTests {

    private final ColumnEntity columnEntity = new ColumnEntity();

    @Test
    void id() {
        columnEntity.setId(1);
        assertEquals(1, columnEntity.getId());
    }

    @Test
    void userId() {
        columnEntity.setBoardId(1);
        assertEquals(1, columnEntity.getBoardId());
    }

    @Test
    void name() {
        columnEntity.setName("ColumnTest");
        assertEquals("ColumnTest", columnEntity.getName());
    }

}

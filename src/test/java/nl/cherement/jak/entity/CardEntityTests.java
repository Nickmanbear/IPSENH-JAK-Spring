package nl.cherement.jak.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CardEntityTests {

    private final CardEntity cardEntity = new CardEntity();

    @Test
    void id() {
        cardEntity.setId(1);
        assertEquals(1, cardEntity.getId());
    }

    @Test
    void userId() {
        cardEntity.setColumnId(1);
        assertEquals(1, cardEntity.getColumnId());
    }

    @Test
    void name() {
        cardEntity.setName("ColumnTest");
        assertEquals("ColumnTest", cardEntity.getName());
    }

    @Test
    void description() {
        cardEntity.setDescription("ColumnTestDescription");
        assertEquals("ColumnTestDescription", cardEntity.getDescription());
    }

    @Test
    void priority() {
        cardEntity.setPriority(1);
        assertEquals(1, cardEntity.getPriority());
    }

    @Test
    void points() {
        cardEntity.setPoints(1);
        assertEquals(1, cardEntity.getPoints());
    }

}

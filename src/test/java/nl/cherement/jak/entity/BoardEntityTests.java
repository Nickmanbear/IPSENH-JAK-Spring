package nl.cherement.jak.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BoardEntityTests {

    private final BoardEntity boardEntity = new BoardEntity();

    @Test
    void id() {
        boardEntity.setId(1);
        assertEquals(1, boardEntity.getId());
    }

    @Test
    void userId() {
        boardEntity.setUserId(1);
        assertEquals(1, boardEntity.getUserId());
    }

    @Test
    void name() {
        boardEntity.setName("BoardTest");
        assertEquals("BoardTest", boardEntity.getName());
    }

}

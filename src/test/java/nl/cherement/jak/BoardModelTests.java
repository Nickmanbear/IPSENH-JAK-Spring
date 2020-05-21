package nl.cherement.jak;

import nl.cherement.jak.model.BoardModel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BoardModalTests {

    private final BoardModel boardModel = new BoardModel();

    @Test
    void id() {
        boardModel.setId(1);
        assertEquals(1, boardModel.getId());
    }

    @Test
    void userId() {
        boardModel.setUserId(1);
        assertEquals(1, boardModel.getUserId());
    }

    @Test
    void name() {
        boardModel.setName("BoardTest");
        assertEquals("BoardTest", boardModel.getName());
    }

}

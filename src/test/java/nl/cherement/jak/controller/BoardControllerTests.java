package nl.cherement.jak.controller;

import nl.cherement.jak.entity.BoardEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BoardControllerTests {

    private final BoardDTO boardDTO = new BoardDTO();

    @Test
    void DTO() {
        boardDTO.setId(1);
        boardDTO.setUserId(1);
        boardDTO.setName("TestBoard");

        assertEquals("BoardEntity [id=" + boardDTO.getId() + ", userId=" + boardDTO.getUserId()
                + ", name=" + boardDTO.getName() + "]", boardDTO.toEntity().toString());
    }

}

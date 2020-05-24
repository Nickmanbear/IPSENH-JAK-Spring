package nl.cherement.jak.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ColumnControllerTests {

    private final ColumnDTO columnDTO = new ColumnDTO();

    @Test
    void DTO() {
        columnDTO.setId(1);
        columnDTO.setBoardId(1);
        columnDTO.setName("TestColumn");

        assertEquals("ColumnEntity [id=" + columnDTO.getId() + ", boardId=" + columnDTO.getBoardId()
                + ", name=" + columnDTO.getName() + "]", columnDTO.toEntity().toString());
    }

}

package nl.cherement.jak.controller;

import nl.cherement.jak.entity.ColumnEntity;
import nl.cherement.jak.service.ColumnService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ColumnControllerTests {

    private final ColumnDTO columnDTO = new ColumnDTO();
    private ColumnEntity card= new ColumnEntity();
    private ColumnEntity card2 = new ColumnEntity();
    private List<ColumnEntity> cards;

    @Autowired
    private ColumnController controller;

    @MockBean
    private ColumnService service;

    @Test
    void DTO() {
        columnDTO.setId(1);
        columnDTO.setBoardId(1);
        columnDTO.setName("TestColumn");

        assertEquals("ColumnEntity [id=" + columnDTO.getId() + ", boardId=" + columnDTO.getBoardId()
                + ", name=" + columnDTO.getName() + "]", columnDTO.toEntity().toString());
    }

}

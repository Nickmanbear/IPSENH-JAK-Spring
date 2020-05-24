package nl.cherement.jak.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CardControllerTests {

    private final CardDTO cardDTO = new CardDTO();

    @Test
    void DTO() {
        cardDTO.setId(1);
        cardDTO.setColumnId(1);
        cardDTO.setName("TestCard");
        cardDTO.setDescription("TestCardDescription");
        cardDTO.setPriority(1);
        cardDTO.setPoints(1);

        assertEquals("CardEntity [id=" + cardDTO.getId() + ", columnId=" + cardDTO.getColumnId() +
                ", name=" + cardDTO.getName() + ", description=" + cardDTO.getDescription()   +
                ", priority=" + cardDTO.getPriority() + ", points=" + cardDTO.getPoints() + "]", cardDTO.toEntity().toString());
    }

}

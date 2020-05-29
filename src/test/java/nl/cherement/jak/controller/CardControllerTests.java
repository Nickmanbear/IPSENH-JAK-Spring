package nl.cherement.jak.controller;

import nl.cherement.jak.entity.CardEntity;
import nl.cherement.jak.entity.ColumnEntity;
import nl.cherement.jak.service.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class CardControllerTests {

    private final CardDTO cardDTO = new CardDTO();
    private final CardEntity card= new CardEntity();
    private final CardEntity card2 = new CardEntity();
    private List<CardEntity> cards;

    @Autowired
    private CardController controller;

    @MockBean
    private CardService service;


    @BeforeEach
    public void initialize(){

        cardDTO.id = 1;
        card.id = 1;
        card2.id = 2;
        cards = new ArrayList<CardEntity>();
        cards.add(card);
        cards.add(card2);

        doReturn(Optional.of(card)).when(service).findById(any());
        doReturn(cards).when(service).findAll();
        doReturn(cards).when(service).getByColumnId(any());
        doNothing().when(service).deleteById(any());
        doReturn(card).when(service).save(any(CardEntity.class));
    }

    @Test
    void DTO() {
        cardDTO.id = 1;
        cardDTO.column = new ColumnEntity();
        cardDTO.name = "TestCard";
        cardDTO.description = "TestCardDescription";
        cardDTO.priority = 1L;
        cardDTO.points = 1;

        assertEquals("CardEntity [id=" + cardDTO.id + ", column=" + cardDTO.column +
                ", name=" + cardDTO.name + ", description=" + cardDTO.description   +
                ", priority=" + cardDTO.priority + ", points=" + cardDTO.points + "]", cardDTO.toEntity().toString());
    }

    @Test
    void findAll() {
        assertSame(cards, controller.findAll());
    }

    @Test
    void byColumn() {
        assertSame(cards, controller.byColumn(1L));
    }

    @Test
    void findById() {
        assertSame(card, controller.findById(1L).get());
    }

    @Test
    void save() {
        assertSame(card, controller.save(cardDTO));
    }

    @Test
    void deleteById() {
        assertSame(HttpStatus.OK, controller.deleteById(1L));
    }
}

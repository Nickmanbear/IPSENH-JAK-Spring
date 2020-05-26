package nl.cherement.jak.controller;

import nl.cherement.jak.entity.CardEntity;
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
    private CardEntity card= new CardEntity();
    private CardEntity card2 = new CardEntity();
    private List<CardEntity> cards;

    @Autowired
    private CardController controller;

    @MockBean
    private CardService service;


    @BeforeEach
    public void initialize(){

        cardDTO.setId(1);
        card.setId(1);
        card2.setId(2);
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
        cardDTO.setId(1);
        cardDTO.setColumnId(1l);
        cardDTO.setName("TestCard");
        cardDTO.setDescription("TestCardDescription");
        cardDTO.setPriority(1l);
        cardDTO.setPoints(1);

        assertEquals("CardEntity [id=" + cardDTO.getId() + ", columnId=" + cardDTO.getColumnId() +
                ", name=" + cardDTO.getName() + ", description=" + cardDTO.getDescription()   +
                ", priority=" + cardDTO.getPriority() + ", points=" + cardDTO.getPoints() + "]", cardDTO.toEntity().toString());
    }

    @Test
    void findAll() {

        assertSame(cards, controller.findAll());
    }

    @Test
    void byColumn() {

        assertSame(cards, controller.byColumn(1l));
    }

    @Test
    void findById() {

        assertSame(card, controller.findById(1l).get());
    }

    @Test
    void save() {

        assertSame(card, controller.save(cardDTO));
    }

    @Test
    void deleteById() {

        assertSame(HttpStatus.OK, controller.deleteById(1l));

    }
}

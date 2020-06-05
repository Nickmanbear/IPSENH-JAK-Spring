package nl.cherement.jak.service;

import nl.cherement.jak.entity.CardEntity;
import nl.cherement.jak.entity.ColumnEntity;
import nl.cherement.jak.repository.CardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class CardServiceTests {

    private List<CardEntity> cards;
    private CardEntity card;

    @Autowired
    private CardService service;

    @MockBean
    private CardRepository repository;

    @BeforeEach
    public void initialize() {
        cards = new ArrayList<>();
        card = new CardEntity();
        card.id = 1;
        ColumnEntity columnEntity = new ColumnEntity();
        columnEntity.id = 1;
        card.column = columnEntity;
        cards.add(card);

        doReturn(cards).when(repository).findByColumnId(any(Long.class));
        doReturn(Optional.of(card)).when(repository).findById(1L);
        doReturn(card).when(repository).save(any(CardEntity.class));
    }

//    @Test
//    void getByColumnId() {
//        assertSame(cards, service.getByColumnId(1L));
//    }
//
//    @Test
//    void save() {
//        Authentication user = mock(Authentication.class);
//        assertEquals(card, service.save(user, card));
//    }
}

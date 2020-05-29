package nl.cherement.jak.service;

import nl.cherement.jak.entity.CardEntity;
import nl.cherement.jak.repository.CardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class CardServiceTests {

    private List<CardEntity> cards;

    @Autowired
    private CardService service;

    @MockBean
    private CardRepository repository;

    @BeforeEach
    public void initialize() {
        CardEntity card = new CardEntity();
        cards = new ArrayList<>();
        card.id = 1;
        cards.add(card);

        doReturn(cards).when(repository).findByColumnId(any(Long.class));
    }

    @Test
    void getByColumnId() {
        assertSame(cards, service.getByColumnId(1L));
    }
}

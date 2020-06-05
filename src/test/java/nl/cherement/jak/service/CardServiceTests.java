package nl.cherement.jak.service;

import nl.cherement.jak.entity.CardEntity;
import nl.cherement.jak.entity.ColumnEntity;
import nl.cherement.jak.entity.EventEntity;
import nl.cherement.jak.repository.CardRepository;
import nl.cherement.jak.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class CardServiceTests {

    private List<CardEntity> cards;
    private CardEntity card;

    @Autowired
    private CardService service;

    @Autowired
    private EventService eventService;

    @MockBean
    private CardRepository repository;

    @MockBean
    private EventRepository eventRepository;

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

    @Test
    void getByColumnId() {
        assertSame(cards, service.getByColumnId(1L));
    }

    @Test
    void save() {
        Authentication user = mock(Authentication.class);
        assertEquals(card, service.save(user, card));

        List<EventEntity> eventEntities = new ArrayList<>();
        when(eventRepository.save(any(EventEntity.class))).then((Answer<EventEntity>) invocation -> {
            EventEntity eventEntity = new EventEntity();
            eventEntities.add(eventEntity);
            return eventEntity;
        });

        CardEntity newCard = new CardEntity();
        newCard.id = 1;
        newCard.column = new ColumnEntity();
        newCard.column.id = 2;

        service.save(user, newCard);
        assertEquals(1, eventEntities.size());
    }
}

package nl.cherement.jak.service;

import nl.cherement.jak.entity.BoardEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class BoardServiceTest {

    @MockBean
    private BoardService boardService;



    @Test
    void findByUserName() {
        List<BoardEntity> boardEntities = new ArrayList<BoardEntity>();
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId(1);

        boardEntities.add(boardEntity);

        when(boardService.findByUserName("admin")).thenReturn(boardEntities);

        assertThat(boardService.findByUserName("admin")).isSameAs(boardEntities);
    }
}
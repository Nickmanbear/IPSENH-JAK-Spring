package nl.cherement.jak.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class BoardServiceTests {

    @Autowired
    private BoardService boardService;


//    @Test
//    void findByUserName() {
//        List<BoardEntity> boardEntities = new ArrayList<BoardEntity>();
//        BoardEntity boardEntity = new BoardEntity();
//        boardEntity.setId(1);
//
//        boardEntities.add(boardEntity);
//
//        boardService.save(boardEntity);
//        Long l = new Long(1);//first way
//        Optional<BoardEntity> boardEntity2 = boardService.findById(l);
//
//        assertEquals(1, boardEntity2.get().getId());
//
//
//    }
}
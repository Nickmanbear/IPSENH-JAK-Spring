package nl.cherement.jak.entity;

import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BoardEntityTests {

    private final BoardEntity boardEntity = new BoardEntity();
    private final List<UserEntity> userEntitys = new ArrayList<UserEntity>();



    @Test
    void id() {
        boardEntity.setId(1);
        assertEquals(1, boardEntity.getId());
    }

    @Test
    void userId() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntitys.add(userEntity);
        boardEntity.setUsers(userEntitys);
        assertEquals(1, boardEntity.getUsers().get(0).getId());
    }

    @Test
    void name() {
        boardEntity.setName("BoardTest");
        boardEntity.setName("BoardTest");
        assertEquals("BoardTest", boardEntity.getName());
    }

}

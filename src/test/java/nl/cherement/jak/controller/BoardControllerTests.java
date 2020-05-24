package nl.cherement.jak.controller;

import nl.cherement.jak.entity.BoardEntity;
import nl.cherement.jak.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BoardControllerTests {

    private final BoardDTO boardDTO = new BoardDTO();
    private final List<UserEntity> userEntitys = new ArrayList<UserEntity>();

    @Test
    void DTO() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntitys.add(userEntity);
        boardDTO.setUsers(userEntitys);

        boardDTO.setId(1);
        boardDTO.setName("TestBoard");

        assertEquals("BoardEntity [id=" + boardDTO.getId() + ", users=" + boardDTO.getUsers()
                + ", name=" + boardDTO.getName() + "]", boardDTO.toEntity().toString());
    }

}

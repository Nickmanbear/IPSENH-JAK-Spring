package nl.cherement.jak.repository;

import nl.cherement.jak.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserRespositoryTest {

    @MockBean
    @Autowired
    UserRespository userRespository;


    @Test
    void findByUsername() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        when(userRespository.findByUsername("admin")).thenReturn(userEntity);

        assertEquals(userRespository.findByUsername("admin"), userEntity);

    }
}
package nl.cherement.jak.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class SecurityConstantsTests {

    private SecurityConstants constants;

    @Mock
    private ClassLoader classLoader;

    @Mock
    private Properties properties;

    @BeforeEach
    public void initialize(){
        classLoader = mock(ClassLoader.class);
        properties = mock(Properties.class);
        constants = SecurityConstants.getInstance();
    }

    @Test
    void getInstance() {
        assertNotNull(constants);
    }

    @Test
    void getsecret() {
        assertTrue(StringUtils.isNotBlank(constants.getSecret()));
    }



}
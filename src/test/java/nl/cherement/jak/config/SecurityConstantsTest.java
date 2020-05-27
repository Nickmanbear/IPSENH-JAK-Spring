package nl.cherement.jak.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SecurityConstantsTest {

    private SecurityConstants constants;

    @Mock
    private ClassLoader classLoader;

    @Mock
    private Properties properties;

    @BeforeEach
    public void initialize(){
        classLoader = mock(ClassLoader.class);
        properties = mock(Properties.class);


    }

    @Test
    void getInstance() {
        constants = SecurityConstants.getInstance();
        assertTrue(constants instanceof SecurityConstants);
    }

    @Test
    void getsecret() {
        constants = SecurityConstants.getInstance();
        assertTrue(StringUtils.isNotBlank(constants.getsecret()));
    }



}
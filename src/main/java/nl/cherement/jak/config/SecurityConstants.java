package nl.cherement.jak.config;



import org.jboss.logging.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

public class SecurityConstants {

    private final Properties properties;

    private static final SecurityConstants INSTANCE = new SecurityConstants();

    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/user/register";

    private SecurityConstants(){
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        properties = new Properties();
        try (InputStream resourceStream = loader.getResourceAsStream("application.properties")) {
            properties.load(resourceStream);
        } catch (IOException e) {
            LOGGER.log(Logger.Level.ERROR, e);
        }
    }

    public static SecurityConstants getInstance() {
        return INSTANCE;
    }

    public String getSecret(){
        return properties.get("spring.secret").toString();
    }
}

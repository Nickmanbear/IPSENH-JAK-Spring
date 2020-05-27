package nl.cherement.jak.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SecurityConstants {

    private static final SecurityConstants INSTANCE = new SecurityConstants();

    private ClassLoader loader;
    private Properties properties;
    private SecurityConstants(){
         loader = Thread.currentThread().getContextClassLoader();
         properties = new Properties();
        try (InputStream resourceStream = loader.getResourceAsStream("application.properties")) {
            properties.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static SecurityConstants getInstance() {
        return INSTANCE;
    }

    public String getsecret(){
        return properties.get("spring.secret").toString();
    }



    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/user/register";
}

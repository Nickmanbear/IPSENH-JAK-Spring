package nl.cherement.jak.config;

public class SecurityConstants {

    private SecurityConstants(){}

    public static final String SECRET = "JjXBGwNhxo1qTGPIZvpZoOXNBJ664NhPgUGaOoHpVI1X35QVs6";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/user/register";
}
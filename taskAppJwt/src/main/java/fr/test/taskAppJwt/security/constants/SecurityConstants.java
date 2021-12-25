package fr.test.taskAppJwt.security.constants;

public class SecurityConstants {

    public static final String SECRET = "mySecret";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRATION_DATE = 864_000_000; // 10 jours

}

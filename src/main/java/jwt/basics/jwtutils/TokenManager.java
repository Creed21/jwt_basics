package jwt.basics.jwtutils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class TokenManager implements Serializable {

    private static final long serialVersionUID = 7008375124389347049L;
    @Value("${jwt.token.expiryTime}")
    public static final long TOKEN_EXPIR = 10 * 60 * 60;
    @Value("${jwt.token.refreshTime}")
    public static final long REFRESH_TOKEN_EXPIR = 10 * 60 * 60;
    @Value("${jwt.token.issuer}")
    public static String TOKEN_ISSUER;

    @Value("${jwt.secret}")
    private String jwtSecret;

    public String generateJwtToken(@NotNull UserDetails userDetails) {
        /**
         * claims are data related to the user's JWT token (subject)
         * for example username, granted authorities, email
         */
        Map<String, Object> claims = new HashMap();
        String authorities = userDetails.getAuthorities().stream()
                                .map(x -> x.getAuthority())
                                .collect(Collectors.joining(", "));

        claims.put("authorization_details", authorities);
        claims.put("refreshToken", createRefreshToken(userDetails));

         String token = Jwts.builder()
                .setIssuer(TOKEN_ISSUER)
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIR))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
        System.out.println("generated JWT TOKEN: " + token);
        return token;
    }

    private String createRefreshToken(UserDetails userDetails) {
        return Jwts.builder()
                .setIssuer(TOKEN_ISSUER)
                .setSubject(userDetails.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIR))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public Boolean validateJwtToken(String token, @NotNull UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

        Boolean isTokenExpired = claims.getExpiration().before(new Date(System.currentTimeMillis()));

        return username.equals(userDetails.getUsername()) && !isTokenExpired;
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

}

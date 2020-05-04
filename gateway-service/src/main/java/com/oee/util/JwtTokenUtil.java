package com.oee.util;
import java.util.Date;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.oee.config.JwtConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtTokenUtil {

	private final JwtConfig jwtConfig;
	
	public JwtTokenUtil(JwtConfig jwtConfig) {
		this.jwtConfig = jwtConfig;
	}
	
    // 1Gün
    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 24 * 60 * 60 * 1000;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtConfig.getSecret().getBytes())
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

//    public String generateToken(User user) {
//        return doGenerateToken(user.getUsername(), user.getAuthorities());
//    }
//
//    private String doGenerateToken(String subject, List<Authority> authorities) {
//        Claims claims = Jwts.claims().setSubject(subject);
//        claims.put("authorities", authorities.stream()
//        		.map(Authority::getRole).collect(Collectors.toList()));
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .setIssuer("http://sessevmez.com")
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS))
//                .signWith(SignatureAlgorithm.HS256, jwtConfig.getSecret().getBytes())
//                .compact();
//    }

    public Boolean validateToken(String token, String usernameFromContext) {
        final String username = getUsernameFromToken(token);
        return (
                username.equals(usernameFromContext)
                        && !isTokenExpired(token));
    }

}
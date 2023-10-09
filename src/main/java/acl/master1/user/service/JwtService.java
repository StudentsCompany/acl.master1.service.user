package acl.master1.user.service;

import java.security.Key;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	public static final String SECRET = "JeSuisLeSelDeLaTerreUneVilleSitueeSurUneMontagneLaLumiereDuMonde";
	
	
	public Jws<Claims> validateToken(final String token){
		Jws<Claims> claimsJws = Jwts.parserBuilder()
				.setSigningKey(getSignKey())
				.build().parseClaimsJws(token);
		return claimsJws;		
	}
	
	public String generateToken(String username) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, username);
	}
	
	private String createToken(Map<String, Object> claims, String username) {
		return Jwts.builder()
				.setClaims(claims) // Claims are just our request headers
				.setSubject(username) 
				.setIssuedAt(new Date(System.currentTimeMillis())) // The date the token is create
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)) 
				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	}
	
	/**
	 * This method sign our secret key
	 * @return
	 */
	private Key getSignKey() {
		byte [] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}

package acl.master1.user.service;

import java.security.Key;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.*;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.security.oauth2.jwt.JwtDecoderFactory;

@Service
public class JwtService {
	
	public static final String SECRET = "JeSuisLeSelDeLaTerreUneVilleSitueeSurUneMontagneLaLumiereDuMonde";

//	final JwtDecoder decoder = new JwtDecoder() {
//
//		@SneakyThrows
//		@Override
//		public Jwt decode(String token) throws JwtException {
//			JWT jwt = JWTParser.parse(token);
//			return createJwt(token, jwt);
//		}
	public String getUsernameByToken(String token) {
		String[] chunks = token.split("\\.");

		Base64.Decoder decoder = Base64.getUrlDecoder();

		String header = new String(decoder.decode(chunks[0]));
		String payload = new String(decoder.decode(chunks[1]));
		String[] str = payload.split(",");
		String res = str[0].split(":")[1];
		res = res.replace("\"", "");
		System.out.println("header : " + header + ", payload : " + payload);
		System.out.println("RES : " + res);
		return res;
	}

	
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
	 * This method sign our secret keyp
	 * @return
	 */
	private Key getSignKey() {
		byte [] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}

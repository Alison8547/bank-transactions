package com.br.bank.security;


import com.br.bank.entity.Client;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TokenService {

    private static final String KEY_CARGO = "CARGO";
    @Value("${jwt-expiration}")
    private String expiration;

    @Value("${jwt-secret}")
    private String secret;

    public String getToken(Client client) {
        LocalDateTime dataLocalDateTime = LocalDateTime.now();
        Date date = Date.from(dataLocalDateTime.atZone(ZoneId.systemDefault()).toInstant());
        LocalDateTime localDateExpiration = dataLocalDateTime.plusDays(Long.parseLong(expiration));
        Date dateExpiration = Date.from(localDateExpiration.atZone(ZoneId.systemDefault()).toInstant());

        String cargo = client.getCargo().getName();

        return Jwts.builder()
                .setIssuer("jwt-authentication")
                .claim(Claims.ID, client.getId().toString())
                .claim(KEY_CARGO, cargo)
                .setIssuedAt(date)
                .setExpiration(dateExpiration)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public UsernamePasswordAuthenticationToken isValid(String token) {
        if (token == null) {
            return null;
        }

        token = token.replace("Bearer ", "");

        Claims chaves = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        String idUser = chaves.get(Claims.ID, String.class);

        List<String> cargos = chaves.get(KEY_CARGO, List.class);

        List<SimpleGrantedAuthority> cargosList = cargos.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();


        return new UsernamePasswordAuthenticationToken(idUser,
                null, cargosList);
    }
}

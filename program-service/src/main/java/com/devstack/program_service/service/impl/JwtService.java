package com.devstack.program_service.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Service
public class JwtService {

    private final String publicKeyString= "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoONC02LMTFP7LUfUcQGHiZxFk7a9+OIu9v7HJsDOhnf5hVR7dIYUBHAGMXzWQBvv/i/H8vIxd2msZfI28RGk6LsgAt5GNtA5yJnGBriqoTl8X5VCorzaH3H4/mMn1jbjm5HwGsS/1s0r7E9T2V+/pCFBF1FWOjZ3LdvdIdslrJzZHjsdgNtRFpbGpmGie78fHeLcUHGpGhCqY1ra1wQMjqJWTxrMQHk2BXFIhbZVU+pNMA8OXdRtbHh73Wch+Xjm4p5NI573AiDc31D0dvvPKM0ndf0H9LJj2/ho5bHFImCzAk98K/z9MZRxMxyAq1uNHNuLjJSU9P6kqTbL+2hFdwIDAQAB";
    public String getEmail(String token){
    //token decode;
        try{
            byte[] keyBytes = Base64.getDecoder().decode(publicKeyString);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(spec);

            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(publicKey)
                    .build()
                    .parseClaimsJws(token);

            Claims body = claimsJws.getBody();
            return body.get("email", String.class);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
}

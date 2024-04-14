package com.vecinet.auth.controllers;

import com.vecinet.auth.dtos.JWTResponseDto;
import com.vecinet.auth.utils.JWTUtils;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import com.vecinet.auth.dtos.UserAccountRequestDto;

@RestController
@Controller
public class AuthenticationController {

    @Autowired JWTUtils jwtUtils;

    @PostMapping("/authenticate")
    public ResponseEntity<?> generateToken(@RequestBody UserAccountRequestDto userAccount) {
         // Token válido por una hora
        try
        {
            JwtBuilder res = Jwts.builder()
                    .setSubject(userAccount.getUsername())
                    .setIssuedAt(new Date())
                    .setExpiration( jwtUtils.getExpirationTime() )
                    .signWith(SignatureAlgorithm.HS256, jwtUtils.getSecret() );

            return ResponseEntity.ok( new JWTResponseDto( res.compact() ));

        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Error al generar el token: " + e.getMessage());
        }
    }

    // Método para validar un token JWT dado
    private boolean validateToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey( jwtUtils.getSecret()).parseClaimsJws(token).getBody();
            // Si la validación es exitosa, el token es válido
            return true;
        } catch (SignatureException e) {
            // Si hay una excepción, el token no es válido
            return false;
        }
    }

}

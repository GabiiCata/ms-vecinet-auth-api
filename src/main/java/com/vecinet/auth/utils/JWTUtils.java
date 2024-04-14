package com.vecinet.auth.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class JWTUtils {

    @Value("${jwt.secret}")
    public String secret;

    @Value("${jwt.expiration}")
    private long expiration;
    public  Date getExpirationTime() {
        return new Date( System.currentTimeMillis() + getExpiration());
    }


}

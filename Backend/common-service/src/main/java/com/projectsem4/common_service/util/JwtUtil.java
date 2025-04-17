package com.projectsem4.common_service.util;

import com.projectsem4.common_service.dto.UserInfor;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;

import java.security.Key;

@UtilityClass
public class JwtUtil {

    public final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    public UserInfor decodeToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        String token = authorizationHeader.substring(7);
        System.out.println(token);
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        UserInfor userInfor = new UserInfor();
        userInfor.setUserId(((Number) claims.get("userId")).longValue());
        userInfor.setUserName(claims.getSubject());
        userInfor.setUserName(claims.get("userName", String.class));
        userInfor.setRole(claims.get("role", Integer.class));

        // Các trường khác sẽ để mặc định hoặc null
        return userInfor;
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

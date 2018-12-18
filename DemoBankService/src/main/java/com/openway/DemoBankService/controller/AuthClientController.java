package com.openway.DemoBankService.controller;

import com.openway.DemoBankService.model.BlacklistedToken;
import com.openway.DemoBankService.repository.BlacklistedTokenRepository;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static com.openway.DemoBankService.security.util.SecurityConstants.*;

@RestController
@RequiredArgsConstructor
public class AuthClientController {
    private final BlacklistedTokenRepository blacklistedTokenRepository;

    @PostMapping("/logoutuser")
    public void logout(@RequestHeader(value = HEADER_STRING) String token){
        Date expirationDate = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getExpiration();
        blacklistedTokenRepository.save( new BlacklistedToken(token, expirationDate));
    }
}

package com.openway.DemoBankService;

import com.openway.DemoBankService.model.BlacklistedToken;
import com.openway.DemoBankService.repository.BlacklistedTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class ScheduledBlacklistTokenClean {
    private final BlacklistedTokenRepository blacklistedTokenRepository;

    @Scheduled(fixedRate = 300_000) //every 5 min
    public void tokenBlacklistCleanup(){
        ArrayList<BlacklistedToken> tokenList = new ArrayList<>(blacklistedTokenRepository.findAll());
        Date currentDate = new Date();
        for (BlacklistedToken token: tokenList) {
            if (token.getExpDate().compareTo(currentDate) < 0){
                blacklistedTokenRepository.delete(token);
            }
        }
    }
}

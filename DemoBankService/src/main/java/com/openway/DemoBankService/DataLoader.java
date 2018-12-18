package com.openway.DemoBankService;

import com.openway.DemoBankService.model.Card;
import com.openway.DemoBankService.model.Client;
import com.openway.DemoBankService.model.Deposit;
import com.openway.DemoBankService.model.Loan;
import com.openway.DemoBankService.repository.CardRepository;
import com.openway.DemoBankService.repository.ClientRepository;
import com.openway.DemoBankService.repository.DepositRepository;
import com.openway.DemoBankService.repository.LoanRepository;
import com.openway.DemoBankService.util.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {
    private final ClientRepository clientRepository;
    private final CardRepository cardRepository;
    private final LoanRepository loanRepository;
    private final DepositRepository depositRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void run(ApplicationArguments args) {
        Client user1 = clientRepository.save(new Client("user1", bCryptPasswordEncoder.encode("admin")));
        cardRepository.save(new Card(149032L, 10005L, CardTypeEnum.DEBIT, CurrencyEnum.EUR, false, user1));
        cardRepository.save(new Card(149033L,10004L, CardTypeEnum.DEBIT,CurrencyEnum.RUB, false, user1));
        cardRepository.save(new Card(149034L,10003L, CardTypeEnum.CREDIT,CurrencyEnum.USD, false, user1));
        loanRepository.save(new Loan(LoanTypeEnum.MORTGAGE, LoanTermEnum.SHORT_TERM, 100_000L, 35_000L, 0.35,"Information1", user1));
        depositRepository.save(new Deposit(100_100L, 0.4, DepositRechargeabilityEnum.RECHARGEABLE, DepositWithdrawalEnum.URGENT,"Information11", user1));

        Client user2 = clientRepository.save(new Client("user2", bCryptPasswordEncoder.encode("12345")));
        cardRepository.save(new Card(1119032L,10001L, CardTypeEnum.CREDIT,CurrencyEnum.EUR, false, user2));
        cardRepository.save(new Card(9849033L,100022L, CardTypeEnum.DEBIT,CurrencyEnum.RUB, true, user2));
        loanRepository.save(new Loan(LoanTypeEnum.CUSTOMER,LoanTermEnum.LONG_TERM,10_000L, 3_500L, 0.15, "Information2", user2));


        Client user3 = clientRepository.save(new Client("user3", bCryptPasswordEncoder.encode("333")));
        cardRepository.save(new Card(549032L,10002L, CardTypeEnum.CREDIT,CurrencyEnum.RUB, false, user3));
        loanRepository.save(new Loan(LoanTypeEnum.COMMERCIAL,LoanTermEnum.MEDIUM_TERM,1_000_000L, 5_000L, 0.5, "Information3", user3));
        depositRepository.save(new Deposit(3_000_000L, 0.11, DepositRechargeabilityEnum.NON_RECHARGEABLE, DepositWithdrawalEnum.ON_DEMAND, "Information33", user3));
    }
}

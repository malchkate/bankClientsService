package com.openway.DemoBankService.controller;

import com.openway.DemoBankService.model.Card;
import com.openway.DemoBankService.model.Deposit;
import com.openway.DemoBankService.model.Loan;
import com.openway.DemoBankService.service.BankInfoRetrievalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class BankOperationsRestController {

    private final BankInfoRetrievalService bankInfoRetrievalService;

    @GetMapping("/cards")
    public List<Card> getCardsList(Principal principal){
        return bankInfoRetrievalService.getCardsListByUserName(principal.getName());
    }

    @GetMapping("/deposits")
    public List<Deposit> getDepositsList(Principal principal){
        return bankInfoRetrievalService.getDepositsListByUserName(principal.getName());
    }

    @GetMapping("/loans")
    public List<Loan> geLoansList(Principal principal){
        return bankInfoRetrievalService.getLoansListByUserName(principal.getName());
    }

    @RequestMapping(value = "/card/{id}", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Card> changeCardBlockingState(@PathVariable("id") long id, Principal principal, @RequestBody Map<String, Object> updates){
        String path = (String) updates.get("path");
        boolean newBlockingState = (Boolean) updates.get("value");
        if (path.equals("isBlocked")){
            Optional<Card> updatedCard = bankInfoRetrievalService.changeCardBlockingState(id, newBlockingState, principal.getName());
            if (updatedCard.isPresent()) {
                return ResponseEntity.ok().body(updatedCard.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }


}

package com.openway.DemoBankService.service;

import com.openway.DemoBankService.model.Card;
import com.openway.DemoBankService.model.Client;
import com.openway.DemoBankService.model.Loan;
import com.openway.DemoBankService.model.Deposit;
import com.openway.DemoBankService.repository.CardRepository;
import com.openway.DemoBankService.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BankInfoRetrievalService {

    private final ClientRepository clientRepository;
    private final CardRepository cardRepository;

    @Transactional
    public List<Card> getCardsListByUserName(String userName){
        Optional<Client> client = clientRepository.findByUsername(userName);
        if (client.isPresent()) {
            return client.get().getCardList();
        } else{
            return new ArrayList<>();  //todo think about right processing of this situation
        }
    }

    @Transactional
    public List<Loan> getCreditsListByUserName(String userName){
        Optional<Client> client = clientRepository.findByUsername(userName);
        if (client.isPresent()) {
            return client.get().getLoanList();
        } else {
            return new ArrayList<>();
        }
    }

    @Transactional
    public List<Deposit> getDepositsListByUserName(String userName){
        Optional<Client> client = clientRepository.findByUsername(userName);
        if (client.isPresent()) {
            return client.get().getDepositList();
        } else {
            return new ArrayList<>();
        }
    }

    @Transactional
    public Optional<Card> changeCardBlockingState(long cardId, boolean newBlockingState, String principalName){
        Optional<Card> card = cardRepository.findById(cardId);
        if (card.isPresent() && card.get().getClient().getUsername().equals(principalName)) {
            card.get().setBlocked(newBlockingState);
            return Optional.of(cardRepository.save(card.get()));
        } else {
            return Optional.empty();
        }
    }
}

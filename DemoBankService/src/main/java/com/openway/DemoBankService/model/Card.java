package com.openway.DemoBankService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.openway.DemoBankService.util.CardTypeEnum;
import com.openway.DemoBankService.util.CurrencyEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long cardNumber;

    private long moneyAmount;

    private CardTypeEnum cardType;

    private CurrencyEnum currency;

    private boolean isBlocked;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="client_id", nullable=false)
    private Client client;


    public Card(long cardNumber, long moneyAmount, CardTypeEnum cardType, CurrencyEnum currency, boolean isBlocked, Client client) {
        this.cardNumber = cardNumber;
        this.moneyAmount = moneyAmount;
        this.cardType = cardType;
        this.currency = currency;
        this.isBlocked = isBlocked;
        this.client = client;
    }
}

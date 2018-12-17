package com.openway.DemoBankService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.openway.DemoBankService.util.DepositRechargeabilityEnum;
import com.openway.DemoBankService.util.DepositWithdrawalEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long depositAmount;

    private double depositInterestRate;

    private DepositRechargeabilityEnum isRechargeable;

    private DepositWithdrawalEnum withdrawalType;

    private String additionalInfo;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="client_id", nullable=false)
    private Client client;


    public Deposit(long depositAmount, double depositInterestRate, DepositRechargeabilityEnum isRechargeable, DepositWithdrawalEnum withdrawalType, String additionalInfo, Client client) {
        this.depositAmount = depositAmount;
        this.depositInterestRate = depositInterestRate;
        this.isRechargeable = isRechargeable;
        this.withdrawalType = withdrawalType;
        this.additionalInfo = additionalInfo;
        this.client = client;
    }
}

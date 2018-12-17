package com.openway.DemoBankService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.openway.DemoBankService.util.LoanTermEnum;
import com.openway.DemoBankService.util.LoanTypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LoanTypeEnum loanType;

    private LoanTermEnum loanTerm;

    private long totalLoanAmount;

    private long paidLoanAmount;

    private double loanInterestRate;

    private String additionalInfo;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="client_id", nullable=false)
    private Client client;


    public Loan(LoanTypeEnum loanType, LoanTermEnum loanTerm, long totalLoanAmount, long paidLoanAmount, double loanInterestRate, String additionalInfo, Client client) {
        this.loanType = loanType;
        this.loanTerm = loanTerm;
        this.totalLoanAmount = totalLoanAmount;
        this.paidLoanAmount = paidLoanAmount;
        this.loanInterestRate = loanInterestRate;
        this.additionalInfo = additionalInfo;
        this.client = client;
    }

}

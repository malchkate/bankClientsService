package com.openway.DemoBankService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    List<Card> cardList;

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    List<Loan> loanList;

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    List<Deposit> depositList;


    public Client(String username, String password){
        this.username = username;
        this.password = password;
    }

}

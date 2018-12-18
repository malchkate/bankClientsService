package com.openway.DemoBankService.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class BlacklistedToken {
    @Id
    private String token;

    private Date expDate;

    public BlacklistedToken(String token, Date expDate) {
        this.token = token;
        this.expDate = expDate;
    }
}

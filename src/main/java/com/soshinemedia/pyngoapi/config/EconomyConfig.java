package com.soshinemedia.pyngoapi.config;

import com.soshinemedia.pyngoapi.repository.ProfileRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class EconomyConfig {
    @Setter @Getter
    private BigDecimal totalPoints;

    @Setter @Getter
    private String primaryReserve;
    @Setter @Getter
    private String secondaryReserve;

    private ProfileRepository account;


    /*public double exchangeRate(){

        Optional<Profile> reserve1 = this.account.findByAccountNumber(this.reserve1);
        Optional<Profile> reserve2 = this.account.findByAccountNumber(this.reserve2);

        if(reserve1.isPresent()&&reserve2.isPresent()) {
            Profile reservePrimary = reserve1.get();
            Profile reserveSecondary = reserve2.get();
            int supply = this.totalPoints - (reservePrimary.GetBalance() + reserveSecondary.getBalance());
            double rate = reserveSecondary.getCash()/supply;
            return rate;
        }else{
            return 0.0;
        }
    }*/

    EconomyConfig(){
        this.totalPoints = new BigDecimal(100000000);
        this.primaryReserve = "0x9ac156723119abb98183b4e6f02a87b7a7115b31";
        this.secondaryReserve = "0xe352E36fD228ddaeA0c28F1Af26d62264b03ac4f";

    }
}

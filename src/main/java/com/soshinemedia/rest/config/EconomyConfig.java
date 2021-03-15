package com.soshinemedia.rest.config;

import com.soshinemedia.rest.domain.Profile;
import com.soshinemedia.rest.repository.ProfileRepository;

import java.util.Optional;

public class EconomyConfig {
    private int totalPoints;
    private String reserve1;
    private String reserve2;
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
        this.totalPoints = 1000000000;
        this.reserve1 = "0x9ac156723119abb98183b4e6f02a87b7a7115b31";
        this.reserve2 = "0xe352E36fD228ddaeA0c28F1Af26d62264b03ac4f";

    }
}

package com.soshinemedia.pyngoapi.Service;

import com.soshinemedia.pyngoapi.config.EconomyConfig;
import com.soshinemedia.pyngoapi.domain.*;
import com.soshinemedia.pyngoapi.repository.ExchangeRateRepository;
import com.soshinemedia.pyngoapi.repository.ProfileRepository;
import com.soshinemedia.pyngoapi.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;
@Service
@NoArgsConstructor
@AllArgsConstructor
public class TransactionService {

    @Autowired
    EconomyConfig eConfig;
    @Autowired
    ProfileRepository account;
    @Autowired
    ExchangeRateRepository exchange;
    @Getter @Setter
    private BigDecimal totalPoints;
    @Autowired
    TransactionRepository repository;

    /*public ResponseEntity<Object> update(@AuthenticationPrincipal UserDetails userDetails, @RequestBody Transaction form) {

        User usr = this.user.findByUsername(userDetails.getUsername()).get();

        Profile sender = usr.getProfile();
        Profile receiver = this.profile.findByAccountNumber(form.getToAddress()).get();

        Float senderNewBalance = sender.getBalance()-form.getAmount();
        Float receiverNewBalance = receiver.getBalance()+form.getAmount();
        Transaction trans;

        if(senderNewBalance > 0) {
            sender.setBalance(senderNewBalance);
            receiver.setBalance(receiverNewBalance);

            this.profile.save(sender);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            trans = this.transaction.save(Transaction.builder()
                    .description(form.getDescription())
                    .fromAddress(sender.getAccountNumber())
                    .toAddress(form.getToAddress())
                    .amount(form.getAmount())
                    .type(TransactionType.TRANSFER)
                    .createdAt(timestamp)
                    .build()
            );
            return ok(trans);
        }else{
            return ok("Insufficient Funds");
        }

    }*/
    public List<Transaction> getAll(String id){
        Pageable sortedByIdDesc =
                PageRequest.of(0, 10, Sort.by("id").descending());
        Iterable <Transaction> it = this.repository.findByFromAddressOrToAddress(id,id,sortedByIdDesc);
        //Iterable <Offer> it = offerRepository.findAll(sortedByIdDesc);
        List <Transaction>transactions = new ArrayList<Transaction>();
        it.forEach(e -> transactions.add(e));

        return transactions;
    }
    public BigDecimal exchangeRate(){

        Optional<ExchangeRate> currentRateOptional = this.exchange.findTopByOrderByIdDesc();
        Optional<Profile> reserve1 = this.account.findByAccountNumber(this.eConfig.getPrimaryReserve());
        Optional<Profile> reserve2 = this.account.findByAccountNumber(this.eConfig.getSecondaryReserve());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        if(currentRateOptional.isPresent()){
            ExchangeRate currentRate = currentRateOptional.get();
            return currentRate.getValue();
        }else if(reserve1.isPresent() && reserve2.isPresent()){
            Profile reservePrimary = reserve1.get();
            Profile reserveSecondary = reserve2.get();
            BigDecimal rate = reservePrimary.getCash();
            if (rate.intValue() > 0) {

                BigDecimal supply = this.eConfig.getTotalPoints();
                BigDecimal sum = reservePrimary.getBalance();
                sum = sum.add(reserveSecondary.getBalance());
                supply = supply.subtract(sum);

                rate = rate.divide(supply, 5, RoundingMode.HALF_UP);

                return rate;
            } else {
                return new BigDecimal(0.001);
            }
        }else{
            return new BigDecimal(0.001);
        }
    }
    public BigDecimal adjustRate(BigDecimal reserve, BigDecimal request,String type){
        BigDecimal adjustment = request;
        adjustment.divide(reserve, 3, RoundingMode.HALF_UP);

        if(type == "selling"){
            return adjustment.multiply(new BigDecimal(-1));
        }else{
            return adjustment;
        }
    }

    public boolean withdraw(){
        return true;
    }

    public boolean deposit(){
        return true;
    }

    public boolean reward(){
        return true;
    }

}

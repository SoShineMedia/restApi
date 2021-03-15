package com.soshinemedia.rest.Service;

import com.soshinemedia.rest.domain.Profile;
import com.soshinemedia.rest.domain.Transaction;
import com.soshinemedia.rest.domain.TransactionType;
import com.soshinemedia.rest.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;

import static org.springframework.http.ResponseEntity.ok;

public class TransactionService {

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

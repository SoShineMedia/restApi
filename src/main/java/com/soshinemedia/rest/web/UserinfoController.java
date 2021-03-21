package com.soshinemedia.rest.web;

import com.soshinemedia.rest.Service.TransactionService;
import com.soshinemedia.rest.config.EconomyConfig;
import com.soshinemedia.rest.domain.*;
import com.soshinemedia.rest.repository.ExchangeRateRepository;
import com.soshinemedia.rest.repository.ProfileRepository;
import com.soshinemedia.rest.repository.TransactionRepository;
import com.soshinemedia.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.net.URI;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.ok;

@RestController()
public class UserinfoController {

    @Autowired
    ProfileRepository profile;
    @Autowired
    UserRepository user;
    @Autowired
    TransactionRepository transaction;
    @Autowired
    TransactionService transactionService;
    @Autowired
    EconomyConfig eConfig;
    @Autowired
    ExchangeRateRepository exchange;

    @GetMapping("/v1/user")
    public ResponseEntity currentUser(@AuthenticationPrincipal UserDetails userDetails){
        User usr = this.user.findByUsername(userDetails.getUsername()).get();
        Optional<Profile> profile =  this.profile.findByUserId(usr.getId());
        if(profile.isPresent()){
            return ok(profile);
        }else{
            return ok("not found");
        }
    }
    @PostMapping("/v1/user")
    public ResponseEntity<Object> update(@AuthenticationPrincipal UserDetails userDetails, @RequestBody Profile form) {

        Optional<Profile> profileOptional = this.profile.findById(form.getId());

        if (!profileOptional.isPresent())
            return ResponseEntity.notFound().build();
        this.profile.save(form);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/v1/transfer")
    public ResponseEntity<Object> transfer(@AuthenticationPrincipal UserDetails userDetails, @RequestBody Transaction form) {

        User usr = this.user.findByUsername(userDetails.getUsername()).get();

        Profile sender = usr.getProfile();
        Profile receiver = this.profile.findByAccountNumber(form.getToAddress()).get();

        BigDecimal senderNewBalance = sender.getBalance();
        senderNewBalance = senderNewBalance.subtract(form.getAmount());
        BigDecimal receiverNewBalance = receiver.getBalance();
        receiverNewBalance = receiverNewBalance.add(form.getAmount());
        Transaction trans;

        if(senderNewBalance.compareTo(BigDecimal.ZERO) > 0) {
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
                    .status(TransactionStatus.SUCCESSFUL)
                    .createdAt(timestamp)
                    .build()
            );
            return ok(trans);
        }else{
            return ok("Insufficient Funds");
        }

    }
    @PostMapping("/v1/deposit")
    public ResponseEntity<Object> deposit(@AuthenticationPrincipal UserDetails userDetails, @RequestBody Transaction form) {

        User usr = this.user.findByUsername(userDetails.getUsername()).get();

        Profile sender = usr.getProfile();
        Profile receiver = this.profile.findByAccountNumber(this.eConfig.getPrimaryReserve()).get();


        BigDecimal senderNewBalance = sender.getBalance();
        senderNewBalance = senderNewBalance.add(form.getAmount());
        BigDecimal receiverNewBalance = receiver.getBalance();
        receiverNewBalance = receiverNewBalance.subtract(form.getAmount());

        Transaction trans;

        if(receiverNewBalance.compareTo(new BigDecimal(100)) > 0) {

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            BigDecimal exchangeRate = this.transactionService.exchangeRate();

            trans = this.transaction.save(Transaction.builder()
                    .description(form.getDescription())
                    .fromAddress(sender.getAccountNumber())
                    .toAddress(this.eConfig.getPrimaryReserve())
                    .amount(form.getAmount())
                    .type(TransactionType.DEBIT)
                    .createdAt(timestamp)
                    .status(TransactionStatus.PENDING)
                    .build()
            );

            BigDecimal value = trans.getAmount();
            BigDecimal eRate = exchangeRate.multiply(new BigDecimal(1.005));
            value = value.multiply(eRate);

            this.exchange.save(ExchangeRate.builder()
                    .value(eRate)
                    .createdAt(timestamp)
                    .build()
            );

            trans.setValue(value);
            BigDecimal receiverCash = receiver.getCash();
            receiver.setCash(receiverCash.add(value));
            sender.setBalance(senderNewBalance);
            receiver.setBalance(receiverNewBalance);

            this.profile.save(sender);
            this.profile.save(receiver);
            this.transaction.save(trans);

            return ok(trans);
        }else{
            return ok("Insufficient Funds");
        }
    }

    @PostMapping("/v1/withdraw")
    public ResponseEntity<Object> withdraw(@AuthenticationPrincipal UserDetails userDetails, @RequestBody Transaction form) {

        User usr = this.user.findByUsername(userDetails.getUsername()).get();

        Profile sender = usr.getProfile();
        Profile receiver = this.profile.findByAccountNumber(this.eConfig.getPrimaryReserve()).get();


        BigDecimal senderNewBalance = sender.getBalance();
        senderNewBalance = senderNewBalance.subtract(form.getAmount());
        BigDecimal receiverNewBalance = receiver.getBalance();
        receiverNewBalance = receiverNewBalance.add(form.getAmount());

        Transaction trans;

        if(senderNewBalance.compareTo(new BigDecimal(100)) > 0) {

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            BigDecimal exchangeRate = this.transactionService.exchangeRate();

            trans = this.transaction.save(Transaction.builder()
                    .description(form.getDescription())
                    .fromAddress(sender.getAccountNumber())
                    .toAddress(this.eConfig.getPrimaryReserve())
                    .amount(form.getAmount())
                    .type(TransactionType.CREDIT)
                    .createdAt(timestamp)
                    .status(TransactionStatus.PENDING)
                    .build()
            );

            BigDecimal value = trans.getAmount();
            BigDecimal eRate = exchangeRate.multiply(new BigDecimal(0.98));
            value = value.multiply(eRate);

            this.exchange.save(ExchangeRate.builder()
                    .value(eRate)
                    .createdAt(timestamp)
                    .build()
            );

            trans.setValue(value);
            BigDecimal receiverCash = receiver.getCash();
            receiver.setCash(receiverCash.subtract(value));
            sender.setBalance(senderNewBalance);
            receiver.setBalance(receiverNewBalance);

            this.profile.save(sender);
            this.profile.save(receiver);
            this.transaction.save(trans);

            return ok(trans);
        }else{
            return ok("Insufficient Funds");
        }

    }

    @GetMapping("/v1/user/logs")
    public ResponseEntity userLogs(@AuthenticationPrincipal UserDetails userDetails){
        Map<Object, Object> model = new HashMap<>();
        model.put("username", userDetails.getUsername());
        model.put("roles", userDetails.getAuthorities()
                .stream()
                .map(a -> ((GrantedAuthority) a).getAuthority())
                .collect(toList())
        );
        return ok(model);
    }

    @GetMapping("/v1/user/transactions")
    public ResponseEntity userTransactions(@AuthenticationPrincipal UserDetails userDetails){
        Map<Object, Object> model = new HashMap<>();
        model.put("username", userDetails.getUsername());
        model.put("roles", userDetails.getAuthorities()
                .stream()
                .map(a -> ((GrantedAuthority) a).getAuthority())
                .collect(toList())
        );
        return ok(model);
    }

    @GetMapping("/v1/user/exchanges")
    public ResponseEntity userExchanges(@AuthenticationPrincipal UserDetails userDetails){
        Map<Object, Object> model = new HashMap<>();
        model.put("username", userDetails.getUsername());
        model.put("roles", userDetails.getAuthorities()
                .stream()
                .map(a -> ((GrantedAuthority) a).getAuthority())
                .collect(toList())
        );
        return ok(model);
    }

    @GetMapping("/v1/user/offers")
    public ResponseEntity userOffers(@AuthenticationPrincipal UserDetails userDetails){
        Map<Object, Object> model = new HashMap<>();
        model.put("username", userDetails.getUsername());
        model.put("roles", userDetails.getAuthorities()
                .stream()
                .map(a -> ((GrantedAuthority) a).getAuthority())
                .collect(toList())
        );
        return ok(model);
    }

    @GetMapping("/v1/user/acceptances")
    public ResponseEntity userAcceptances(@AuthenticationPrincipal UserDetails userDetails){
        Map<Object, Object> model = new HashMap<>();
        model.put("username", userDetails.getUsername());
        model.put("roles", userDetails.getAuthorities()
                .stream()
                .map(a -> ((GrantedAuthority) a).getAuthority())
                .collect(toList())
        );
        return ok(model);
    }

}

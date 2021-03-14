package com.soshinemedia.rest.web;

import com.soshinemedia.rest.domain.Offer;
import com.soshinemedia.rest.domain.Profile;
import com.soshinemedia.rest.domain.User;
import com.soshinemedia.rest.repository.ProfileRepository;
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
import java.net.URI;
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

    @GetMapping("/v1/user")
    public ResponseEntity currentUser(@AuthenticationPrincipal UserDetails userDetails){
        Map<Object, Object> model = new HashMap<>();
        model.put("username", userDetails.getUsername());

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

        Optional<Offer> offerOptional = this.Offer.findById(id);

        if (!offerOptional.isPresent())
            return ResponseEntity.notFound().build();

        form.setId(id);


        this.Offer.save(form);
        return ResponseEntity.noContent().build();
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

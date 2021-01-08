package com.soshinemedia.rest.Service;

import com.soshinemedia.rest.domain.Acceptance;
import com.soshinemedia.rest.domain.Offer;

import com.soshinemedia.rest.repository.AcceptanceRepository;
import com.soshinemedia.rest.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfferService {

    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private AcceptanceRepository acceptanceRepository;

    public List<Offer> findAll() {

        Iterable <Offer> it = offerRepository.findAll();

        List <Offer>offers = new ArrayList<Offer>();
        it.forEach(e -> offers.add(e));

        return offers;
    }

    public List<Acceptance> findAllAcceptances() {

        Iterable <Acceptance> it = acceptanceRepository.findAll();

        List <Acceptance> acceptances = new ArrayList<Acceptance>();
        it.forEach(e -> acceptances.add(e));

        return acceptances;
    }

    public void deleteById(Long userId) {

        //userRepository.deleteById(userId);
    }
}

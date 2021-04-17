package com.soshinemedia.pyngoapi.Service;

import com.soshinemedia.pyngoapi.domain.Acceptance;
import com.soshinemedia.pyngoapi.domain.Offer;

import com.soshinemedia.pyngoapi.repository.AcceptanceRepository;
import com.soshinemedia.pyngoapi.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OfferService {

    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private AcceptanceRepository acceptanceRepository;

    public List<Offer> findAll() {

        Pageable sortedByIdDesc =
                PageRequest.of(0, 10, Sort.by("id").descending());
        Iterable <Offer> it = offerRepository.findAll(sortedByIdDesc);

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

    public Offer save(Offer offer){
        return offerRepository.save(offer);
    }

    public Optional<Offer> findById(Long id){
        Optional<Offer> offer = offerRepository.findById(id);

        //if (!offer.isPresent())
        //    throw new NotFoundException("id-" + id);

        return offer;
    }
    public void delete(Long id) {
        offerRepository.deleteById(id);
    }
}

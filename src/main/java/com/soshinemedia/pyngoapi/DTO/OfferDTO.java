package com.soshinemedia.pyngoapi.DTO;

import com.soshinemedia.pyngoapi.domain.OfferType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.sql.Timestamp;

public class OfferDTO {

        @Getter @Setter
        private Long id;
        @Getter @Setter
        private String description;
        @Getter @Setter
        private OfferType type;
        @Getter @Setter
        private Timestamp created_at;
        @Getter @Setter
        private Timestamp updated_at;

        @Getter
        @Setter
        private Float Lat;
        @Getter @Setter
        private Float Lng;
        @Getter @Setter
        private Boolean pin;
        @Getter @Setter
        private long duration;
        @Getter @Setter
        private int takers;
        @Getter @Setter
        private float reward;

        @Getter @Setter
        private Long owner_id;
        @Getter @Setter
        private String owner;

        @Getter @Setter
        private boolean isaccepted;
        @Getter @Setter
        private boolean isacceptable;
        @Getter @Setter
        private long start;
        @Getter @Setter
        private long end;
}

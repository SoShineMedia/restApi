package com.soshinemedia.rest.DTO;

import com.soshinemedia.rest.domain.OfferType;
import com.soshinemedia.rest.domain.Profile;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
        private float duration;
        @Getter @Setter
        private int takers;

        @Getter @Setter
        private Long owner_id;
        @Getter @Setter
        private String owner;
}

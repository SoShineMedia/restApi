
package com.soshinemedia.rest.repository;

        import com.soshinemedia.rest.domain.Brand;
        import com.soshinemedia.rest.domain.Offer;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.repository.CrudRepository;
        import org.springframework.data.rest.core.annotation.RepositoryRestResource;

        import java.util.List;

@RepositoryRestResource(path = "offers", collectionResourceRel = "offers", itemResourceRel = "offer")
public interface OfferRepository extends CrudRepository<Offer, Long> {
    List<Offer> findById(Offer id);
}

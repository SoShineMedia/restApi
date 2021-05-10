
package com.soshinemedia.pyngoapi.repository;

        import com.soshinemedia.pyngoapi.domain.Offer;
        import org.springframework.data.repository.CrudRepository;
        import org.springframework.data.repository.PagingAndSortingRepository;
        import org.springframework.data.rest.core.annotation.RepositoryRestResource;

        import java.util.List;

@RepositoryRestResource(path = "offers", collectionResourceRel = "offers", itemResourceRel = "offer")
public interface OfferRepository extends PagingAndSortingRepository<Offer, Long> {
    List<Offer> findById(Offer id);
}

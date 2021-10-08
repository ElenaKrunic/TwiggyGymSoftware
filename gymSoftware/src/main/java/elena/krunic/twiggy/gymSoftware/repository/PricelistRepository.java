package elena.krunic.twiggy.gymSoftware.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import elena.krunic.twiggy.gymSoftware.model.Pricelist;

@Repository
public interface PricelistRepository extends JpaRepository<Pricelist, Long> {

}

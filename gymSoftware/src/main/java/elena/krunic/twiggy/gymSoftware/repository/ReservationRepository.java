package elena.krunic.twiggy.gymSoftware.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import elena.krunic.twiggy.gymSoftware.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}

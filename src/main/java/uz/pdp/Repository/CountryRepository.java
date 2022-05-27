package uz.pdp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.Entity.Country;

public interface CountryRepository extends JpaRepository<Country,Integer> {
}

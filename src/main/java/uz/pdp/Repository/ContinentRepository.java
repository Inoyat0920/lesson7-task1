package uz.pdp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.Entity.Continent;

public interface ContinentRepository extends JpaRepository<Continent,Integer> {
    boolean existsByName(String name);
}

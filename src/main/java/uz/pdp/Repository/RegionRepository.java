package uz.pdp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.Entity.Region;
@Repository
public interface RegionRepository extends JpaRepository<Region,Integer> {
}

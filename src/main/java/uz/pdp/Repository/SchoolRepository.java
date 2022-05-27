package uz.pdp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.Entity.school;

public interface SchoolRepository extends JpaRepository<school,Integer> {
    boolean existsByName(String name);
}

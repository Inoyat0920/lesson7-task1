package uz.pdp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.Entity.subject;

@Repository
public interface subjektRepository extends JpaRepository<subject,Integer> {
}

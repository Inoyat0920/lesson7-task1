package uz.pdp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.Entity.Group;

@Repository
public interface groupRepository extends JpaRepository<Group,Integer> {
}

package uz.pdp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.Entity.timeTable;

@Repository
public interface timeTableRepository extends JpaRepository<timeTable,Integer>{
}

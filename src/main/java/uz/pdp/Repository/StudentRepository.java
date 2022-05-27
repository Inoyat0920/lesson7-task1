package uz.pdp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.Entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
}

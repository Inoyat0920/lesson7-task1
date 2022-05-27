package uz.pdp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.Entity.Addres;
@Repository
public interface AddressRepository extends JpaRepository<Addres,Integer> {
}

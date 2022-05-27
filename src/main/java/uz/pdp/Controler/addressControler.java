package uz.pdp.Controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.Entity.Addres;
import uz.pdp.Entity.District;
import uz.pdp.Repository.AddressRepository;
import uz.pdp.Repository.DistrictRepository;
import uz.pdp.Yordamchi.addressDto;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("address")
public class addressControler {
    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    AddressRepository addressRepository;

    @GetMapping
    public List<Addres> findall() {
        List<Addres> all = addressRepository.findAll();
        return all;
    }

    @GetMapping("/{id}")
    public Addres bitta(@PathVariable Integer id) {
        Optional<Addres> optionalAddres = addressRepository.findById(id);
        if (optionalAddres.isPresent()) {
            return optionalAddres.get();
        }
        return new Addres();

    }

    @DeleteMapping("/{id}")
    public String deletaddres(@PathVariable Integer id) {
        Optional<Addres> addres = addressRepository.findById(id);
        if (addres.isPresent()) {
            addressRepository.deleteById(id);
            return "deleted";
        }
        return "topilmadi";
    }

    @PostMapping
    public String addAddress(@RequestBody addressDto addressDto1) {
        Optional<District> district = districtRepository.findById(addressDto1.getDistrict_id());
        if (district.isPresent())
        {
            Addres addres=new Addres();
            addres.setDistrict(district.get());
            addres.setStreet(addressDto1.getStreet());
            addres.setHome_number(addressDto1.getHome_number()+"");
            addressRepository.save(addres);
            return "save";
        }
        return "topilmadi";

    }

    @PutMapping("/{id}")
    public String editAddress(@PathVariable Integer id,@RequestBody addressDto addressDto1)
    {
        Optional<Addres> addres1 = addressRepository.findById(id);
        if (addres1.isEmpty()){
            return "topilmadi";
        }
        Optional<District> district = districtRepository.findById(addressDto1.getDistrict_id());
        if (district.isPresent())
        {
            Addres addres=addres1.get();
            addres.setDistrict(district.get());
            addres.setStreet(addressDto1.getStreet());
            addres.setHome_number(addressDto1.getHome_number()+"");
            addressRepository.save(addres);
            return "edit";
        }
        return "topilmadi";
    }


}

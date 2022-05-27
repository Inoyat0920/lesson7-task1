package uz.pdp.Controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.Entity.Addres;
import uz.pdp.Entity.school;
import uz.pdp.Repository.AddressRepository;
import uz.pdp.Repository.SchoolRepository;
import uz.pdp.Yordamchi.SchoolDto;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/school")
public class SchoolControler {
    @Autowired
    SchoolRepository schoolRepository;

    @Autowired
    AddressRepository addressRepository;

    @PostMapping
    public String addSchool(@RequestBody SchoolDto school1) {
        Optional<Addres> addres = addressRepository.findById(school1.getAddress_id());
        if (addres.isEmpty()) {
            return "not found address";
        }
        boolean b = schoolRepository.existsByName(school1.getName());
        if (b) {
            return "school mavjud";
        }
        school school = new school();
        school.setName(school1.getName());
        school.setAddres(addres.get());
        schoolRepository.save(school);
        return "add";
    }

    @GetMapping
    public List<school> findall() {
        List<school> all = schoolRepository.findAll();
        return all;
    }

    @GetMapping("/{id}")
    public school byid(@PathVariable Integer id) {
        Optional<school> byId = schoolRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        return new school();
    }

    @DeleteMapping("/{id}")
    public String deletSchool(@PathVariable Integer id) {
        Optional<school> byId = schoolRepository.findById(id);
        if (byId.isPresent()) {
            schoolRepository.delete(byId.get());
            return "deleted";
        }
        return "school not found";
    }

    @PutMapping("/{id}")
    public String editSchool(@PathVariable Integer id, @RequestBody SchoolDto schoolDto) {
        Optional<school> school = schoolRepository.findById(id);
        if (school.isEmpty()) {
            return "school not found";
        }
        Optional<Addres> byId = addressRepository.findById(schoolDto.getAddress_id());
        if (byId.isEmpty()) {return "not found address";}

            uz.pdp.Entity.school school1 = school.get();
        school1.setAddres(byId.get());
        school1.setName(schoolDto.getName());
        schoolRepository.save(school1);
        return "edit";
        }
    }






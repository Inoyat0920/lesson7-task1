package uz.pdp.Controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.Entity.Continent;
import uz.pdp.Entity.Country;
import uz.pdp.Repository.ContinentRepository;
import uz.pdp.Repository.CountryRepository;
import uz.pdp.Yordamchi.Dto;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Country")
public class CountryControler {
    @Autowired
    CountryRepository countryRepository;
    @Autowired
    ContinentRepository continentRepository;


    @GetMapping
    public List<Country> findall() {
        List<Country> all = countryRepository.findAll();
        return all;
    }
    @GetMapping("/{id}")
    public Country bitta(@PathVariable Integer id)
    {
        Optional<Country> optionalCountry = countryRepository.findById(id);
        if (optionalCountry.isPresent())
        {
            return optionalCountry.get();
        }
        return new Country();
    }

    @PostMapping
    public String add(@RequestBody Dto dto)
    {
        Optional<Continent> byId = continentRepository.findById(dto.getContinentId());
        if (byId.isPresent())
        {
            Country country=new Country();
            country.setName(dto.getName());
            country.setContinent(byId.get());
            countryRepository.save(country);
        }
        return "not";
    }

    @DeleteMapping("/{id}")
    public String delet(@PathVariable Integer id)
    {
        Optional<Country> byId = countryRepository.findById(id);
        if (byId.isPresent())
        {
            countryRepository.delete(byId.get());
            return "deleted";
        }
        return "topilmadi";
    }

    @PutMapping("/{id}")
    public String editing(@PathVariable Integer id,@RequestBody Dto dto)
    {
        Optional<Country> byId = countryRepository.findById(id);
        if (byId.isPresent()) {
            Country country = byId.get();
            Optional<Continent> byId1 = continentRepository.findById(dto.getContinentId());
            country.setContinent(byId1.get());
            country.setName(dto.getName());
            countryRepository.save(country);
            return "editing";
        }
        return "topilmadi";
    }

}

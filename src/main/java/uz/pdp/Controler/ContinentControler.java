package uz.pdp.Controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.Entity.Continent;
import uz.pdp.Repository.ContinentRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Continent")
public class ContinentControler {

    @Autowired
    ContinentRepository continentRepository;

    @GetMapping
    public List<Continent> allContinent()
    {
        List<Continent> all = continentRepository.findAll();
        return all;
    }
    @PostMapping
    public String addContinent(@RequestBody Continent continent){
        //Shunday nomli qita mavjudmi
        boolean existsByName = continentRepository.existsByName(continent.getName());
        if (existsByName)
            return "This continent exists!";

        //saqlash
        Continent savedContinent = continentRepository.save(continent);
        return "Continent saved. ID:" + savedContinent.getId();
    }

    @GetMapping("/{id}")
    public Continent olish(@PathVariable Integer id)
    {
        Optional<Continent> byId = continentRepository.findById(id);
        if (byId.isEmpty())
        {
            return new Continent();
        }
        return byId.get();
    }
    @DeleteMapping ("/{id}")
    public String ochirish(@PathVariable Integer id)
    {
        Optional<Continent> byId = continentRepository.findById(id);
        if (byId.isEmpty())
        {
            return "topilmadi";
        }
        continentRepository.deleteById(id);
        return "deleted";
    }

    @PutMapping("/{id}")
    public String taxrirlash(@PathVariable Integer id,@RequestBody Continent continent)
    {
        Optional<Continent> optionalContinent = continentRepository.findById(id);
        if (optionalContinent.isEmpty())
        {
            Continent continent1=optionalContinent.get();
            continent1.setName(continent.getName());
            continentRepository.save(continent1);
            return "saved";
        }
        return "no";
    }


}

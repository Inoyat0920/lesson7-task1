package uz.pdp.Controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.Entity.Country;
import uz.pdp.Entity.Region;
import uz.pdp.Repository.CountryRepository;
import uz.pdp.Repository.RegionRepository;
import uz.pdp.Yordamchi.regionDto;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("Region")
public class RegionControler {
    @Autowired
    RegionRepository regionRepository;

    @Autowired
    CountryRepository countryRepository;

    @GetMapping
    public List<Region> all()
    {
        List<Region> all = regionRepository.findAll();
        return all;
    }

    @GetMapping("/{id}")
    public Region bitta(@PathVariable Integer id)
    {
        Optional<Region> optionalRegion = regionRepository.findById(id);
        if (optionalRegion.isPresent())
        {
            return optionalRegion.get();
        }
        return new Region();
    }

    @PostMapping
    public String addRegion(@RequestBody regionDto regionDto1)
    {
        Region region=new Region();
        countryRepository.findById(regionDto1.getCountry_id());
        region.setName(regionDto1.getName());
        regionRepository.save(region);
        return "add";
    }
    @DeleteMapping("/{id}")
    public String deletrEGION(@PathVariable Integer id)
    {
        Optional<Region> byId = regionRepository.findById(id);
        if (byId.isPresent())
        {
            regionRepository.deleteById(id);
            return "ochirildi8";
        }
        return "topilmadi";
    }

    @PutMapping("/{id}")
    public String editRegion(@PathVariable Integer id,@RequestBody regionDto regionDto)
    {
        Optional<Region> byId = regionRepository.findById(id);
        if (byId.isPresent())
        {
            Region region=byId.get();
            Optional<Country> country = countryRepository.findById(regionDto.getCountry_id());
            region.setCountry(country.get());
            region.setName(regionDto.getName());
            regionRepository.save(region);
            return "edit";
        }
        return "topilmadi";
    }
}

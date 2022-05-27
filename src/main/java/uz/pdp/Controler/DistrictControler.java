package uz.pdp.Controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.Entity.District;
import uz.pdp.Entity.Region;
import uz.pdp.Repository.DistrictRepository;
import uz.pdp.Repository.RegionRepository;
import uz.pdp.Yordamchi.districtDto;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/distrit")
public class DistrictControler {
    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    RegionRepository regionRepository;

    @GetMapping
    public List<District> alldstrit() {
        List<District> all = districtRepository.findAll();
        return all;
    }

    @GetMapping("/{id}")
    public District bitta(@PathVariable Integer id) {
        Optional<District> district = districtRepository.findById(id);
        if (district.isPresent()) {
            return district.get();
        }
        return new District();
    }

    @DeleteMapping("/{id}")
    public String deletdistrt(@PathVariable Integer id) {
        Optional<District> byId = districtRepository.findById(id);
        if (byId.isPresent()) {
            districtRepository.delete(byId.get());
            return "deleted";
        }
        return "topilmadi";
    }

    @PostMapping()
    public String addDistrict(@RequestBody districtDto dto) {
        Optional<Region> byId = regionRepository.findById(dto.region_id);
        if (byId.isEmpty())
            return "topilmadi";
        District district = new District();
        district.setRegion(byId.get());
        district.setName(dto.getName());
        districtRepository.save(district);
        return "add";
    }

    @PutMapping("/{id}")
    public String editDistrict(@PathVariable Integer id, @RequestBody districtDto dto) {
        Optional<Region> byId = regionRepository.findById(dto.region_id);
        if (byId.isEmpty())
            return "topilmadi";
        Optional<District> byId1 = districtRepository.findById(id);
        if (byId1.isEmpty())
            return "mavjud emas";
        District district =byId1.get();

        district.setRegion(byId.get());
        district.setName(dto.getName());
        districtRepository.save(district);
        return "edit";
    }
}

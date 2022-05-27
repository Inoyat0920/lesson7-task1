package uz.pdp.Controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.Entity.Group;
import uz.pdp.Entity.school;
import uz.pdp.Repository.SchoolRepository;
import uz.pdp.Repository.groupRepository;
import uz.pdp.Yordamchi.groupDto;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/group")
public class groupControler {
    @Autowired
    groupRepository groupRepository1;

    @Autowired
    SchoolRepository schoolRepository;

    @GetMapping
    public List<Group> findall()
    {
        List<Group> all = groupRepository1.findAll();
        return all;
    }

    @GetMapping("/{id}")
    public Group bitta(@PathVariable Integer id)
    {
        Optional<Group> byId = groupRepository1.findById(id);
        if (byId.isPresent())
            return byId.get();

        return new Group();
    }

    @PostMapping
    public String addgroup(@RequestBody groupDto groupDto)
    {
        Optional<school> school = schoolRepository.findById(groupDto.getSchool_id());
        if (school.isEmpty())
            return "not fount school";

        Group groups=new Group();
        groups.setName(groupDto.getName());
        groups.setSchool1(school.get());
        groupRepository1.save(groups);
        return "add";
    }

    @DeleteMapping("/{id}")
    public String deletGroup(@PathVariable Integer id)
    {
        Optional<Group> byId = groupRepository1.findById(id);
        if (byId.isPresent())
        {
            groupRepository1.delete(byId.get());
            return "deleted";
        }
        return "not found group";
    }

    @PutMapping("/{id}")
    public String edit(@PathVariable Integer id,@RequestBody groupDto groupDto)
    {
        Optional<Group> byId = groupRepository1.findById(id);
        if (byId.isEmpty())
            return "not found";

        Optional<school> school = schoolRepository.findById(groupDto.getSchool_id());
        if (school.isPresent())
        {
            Group groups=byId.get();
            groups.setName(groupDto.getName());
            groups.setSchool1(school.get());
            groupRepository1.save(groups);
            return "edit";
        }
        return "not found school";

    }
}

package uz.pdp.Controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.Entity.Teacher;
import uz.pdp.Entity.subject;
import uz.pdp.Repository.subjektRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subjekt")
public class SubjektControler {

    @Autowired
    uz.pdp.Repository.subjektRepository subjektRepository;

    @GetMapping
    public List<subject> findall()
    {
        List<subject> teachers = subjektRepository.findAll();
        return teachers;
    }

    @GetMapping("/{id}")
    public subject bitta(@PathVariable Integer id)
    {
        Optional<subject> subject = subjektRepository.findById(id);
        if (subject.isPresent())
        {
            return subject.get();
        }

        return new subject();

    }

    @DeleteMapping("/{id}")
    public String deletTeacher(@PathVariable Integer id)
    {
        Optional<subject> subject = subjektRepository.findById(id);
        if (subject.isPresent()){
            subjektRepository.delete(subject.get());
            return "delet";
        }
        return "not found teacher";
    }

    @PostMapping
    public String addSubject(@RequestBody subject subject)
    {
        subjektRepository.save(subject);
        return "add";
    }

    @PutMapping("/{id}")
    public String editSubject(@PathVariable Integer id,@RequestBody subject subject2)
    {
        Optional<subject> subject = subjektRepository.findById(id);
        if (subject.isPresent())
        {
            subject subject1=subject.get();
            subject1.setName(subject2.getName());
            subjektRepository.save(subject1);
            return "edit";
        }
        return "not found teacher";
    }
}

package uz.pdp.Controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.Entity.Student;
import uz.pdp.Entity.Teacher;
import uz.pdp.Repository.teacherRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teacher")
public class TeacherControler {
    @Autowired
    uz.pdp.Repository.teacherRepository teacherRepository;
    @GetMapping
    public List<Teacher> findall()
    {
        List<Teacher> teachers = teacherRepository.findAll();
        return teachers;
    }

    @GetMapping("/{id}")
    public Teacher bitta(@PathVariable Integer id)
    {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (teacher.isPresent())
        {
            return teacher.get();
        }

        return new Teacher();

    }

    @DeleteMapping("{id}")
    public String deletTeacher(@PathVariable Integer id)
    {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (teacher.isPresent()){
            teacherRepository.delete(teacher.get());
            return "delet";
        }
        return "not found teacher";
    }

    @PostMapping
    public String addTeacher(@RequestBody Teacher teacher)
    {
        teacherRepository.save(teacher);
        return "add";
    }

    @PutMapping("{id}")
    public String editTeacher(@PathVariable Integer id,@RequestBody Teacher teacher2)
    {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (teacher.isPresent())
        {
            Teacher teacher1=teacher.get();
            teacher1.setName(teacher2.getName());
            teacherRepository.save(teacher1);
            return "edit";
        }
        return "not found teacher";
    }

}

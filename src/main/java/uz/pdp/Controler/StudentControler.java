package uz.pdp.Controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.Entity.Student;
import uz.pdp.Entity.Group;
import uz.pdp.Repository.StudentRepository;
import uz.pdp.Repository.groupRepository;
import uz.pdp.Yordamchi.StudentDto;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Student")
public class StudentControler {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    groupRepository groupRepository;

    @GetMapping
    public List<Student> findall()
    {
        List<Student> students = studentRepository.findAll();
        return students;
    }

    @GetMapping("/{id}")
    public Student bitta(@PathVariable Integer id)
    {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent())
        {
            return optionalStudent.get();
        }
        return new Student();
    }

    @DeleteMapping("/{id}")
    public String deletStudent(@PathVariable Integer id)
    {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent())
        {
            studentRepository.delete(student.get());
            return "deleted";
        }
        return "not found student";
    }

    @PostMapping
    public String addStudent(@RequestBody StudentDto studentDto)
    {
        Optional<Group> groups = groupRepository.findById(studentDto.getGroup_id());
        if (groups.isEmpty())
        {
            return "not found group";
        }
        Student student=new Student();
        student.setName(studentDto.getName());
        student.setGroups1(groups.get());
        studentRepository.save(student);
        return "add";
    }

    @PutMapping("/{id}")
    public String editStudent(@PathVariable Integer id,@RequestBody StudentDto studentDto)
    {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty())
        {
            return "not found student";
        }
        Optional<Group> byId = groupRepository.findById(studentDto.getGroup_id());
        if (byId.isEmpty())
        {
            return "not found group";
        }
        Student student1=student.get();
        student1.setName(studentDto.getName());
        student1.setGroups1(byId.get());
        studentRepository.save(student1);
        return "edit";

    }

}

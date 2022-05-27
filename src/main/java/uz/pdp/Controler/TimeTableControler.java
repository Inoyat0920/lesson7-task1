package uz.pdp.Controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.Entity.Teacher;
import uz.pdp.Entity.Group;
import uz.pdp.Entity.subject;
import uz.pdp.Entity.timeTable;
import uz.pdp.Yordamchi.timeTableDto;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/timeTable")
public class TimeTableControler {

    @Autowired
    uz.pdp.Repository.timeTableRepository timeTableRepository;

    @Autowired
    uz.pdp.Repository.subjektRepository subjektRepository;

    @Autowired
    uz.pdp.Repository.teacherRepository teacherRepository;

    @Autowired
    uz.pdp.Repository.groupRepository groupRepository;

    @GetMapping
    public List<timeTable> findall()
    {
        List<timeTable> tables = timeTableRepository.findAll();
        return tables;
    }

    @GetMapping("/{id}")
    public timeTable bitta(@PathVariable Integer id){
        Optional<timeTable> timeTable = timeTableRepository.findById(id);
        if (timeTable.isPresent())
        {
            return timeTable.get();
        }
        return new timeTable();

    }

    @DeleteMapping("/{id}")
    public String deletTimeTable(@PathVariable Integer id)
    {
        Optional<timeTable> optionalTimeTable = timeTableRepository.findById(id);
        if (optionalTimeTable.isPresent())
        {
            timeTableRepository.delete(optionalTimeTable.get());
            return "deleted";
        }
        return "not found timeTable";
    }

    @PostMapping
    public String addTimeTable(@RequestBody timeTableDto dto)
    {
        if (dto.getDay()>7)
        {
            return "not found day";
        }
        Optional<subject> subject = subjektRepository.findById(dto.getSubject_id());
        if (subject.isEmpty()){
            return "not found subject";
        }
        Optional<Teacher> teacher = teacherRepository.findById(dto.getTeacher_id());
        if (teacher.isEmpty())
        {
            return "not found teacher";
        }
        Optional<Group> groups = groupRepository.findById(dto.getGroup_id());
        if (groups.isEmpty())
        {
            return "not found groups";
        }
        timeTable timeTable=new timeTable();
        timeTable.setDay(dto.getDay());
        timeTable.setGroups(groups.get());
        timeTable.setSubject(subject.get());
        timeTable.setTeacher(teacher.get());
        timeTableRepository.save(timeTable);
        return "add";
    }

    @PutMapping("/{id}")
    public String editTimeTable(@PathVariable Integer id,@RequestBody timeTableDto dto)
    {
        Optional<timeTable> byId = timeTableRepository.findById(id);
        if (dto.getDay()>7)
        {
            return "not found day";
        }
        if (byId.isEmpty())
        {
            return "not found timeTable";
        }
        Optional<subject> subject = subjektRepository.findById(dto.getSubject_id());
        if (subject.isEmpty()){
            return "not found subject";
        }
        Optional<Teacher> teacher = teacherRepository.findById(dto.getTeacher_id());
        if (teacher.isEmpty())
        {
            return "not found teacher";
        }
        Optional<Group> groups = groupRepository.findById(dto.getGroup_id());
        if (groups.isEmpty())
        {
            return "not found groups";
        }
        timeTable timeTable=byId.get();
        timeTable.setDay(dto.getDay());
        timeTable.setGroups(groups.get());
        timeTable.setSubject(subject.get());
        timeTable.setTeacher(teacher.get());
        timeTableRepository.delete(timeTable);
        return "deleted";
    }
}

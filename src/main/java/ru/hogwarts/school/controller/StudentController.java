package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.mapper.AvatarMapper;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RestController
@RequestMapping(value = "/student")
public class StudentController {
    private final StudentService studentService;


    public StudentController(StudentService studentService, AvatarMapper avatarMapper) {
        this.studentService = studentService;

    }

    @GetMapping("{id}")
    public Student get(@PathVariable Long id) {
        return studentService.get(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> delete(@PathVariable Long id) {
        studentService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public Student add(@RequestBody Student student) {
        return studentService.add(student);
    }

    @PutMapping("{id}")
    public Student update(@PathVariable Long id, @RequestBody Student student) {
        return studentService.update(id, student);
    }

    @GetMapping
    public List<Student> getByAge(int age) {
        return studentService.getByAge(age);
    }

    @GetMapping("findByAgeBetween")
    public List<Student> findByAgeBetween(int ageFrom, int ageTo) {
        return studentService.findByAgeBetween(ageFrom, ageTo);
    }

    @GetMapping("getFaculty")
    public Faculty getFaculty(Long id) {
        return studentService.getFaculty(id);
    }

    @GetMapping("getStudentCount")
    public int getStudentCount() {
        return studentService.getStudentCount();
    }

    @GetMapping("getAvgYears")
    public int getAvgYears() {
        return studentService.getAvgYears();
    }

    @GetMapping("getLastFive")
    public List<Student> getLastFive() {
        return studentService.getLastFive();
    }


    //-----------------------------for avatar------------------------------//

}

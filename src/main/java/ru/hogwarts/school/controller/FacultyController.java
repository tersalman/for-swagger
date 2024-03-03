package ru.hogwarts.school.controller;

import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/faculty")
public class FacultyController {


    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")
    public Faculty get(@PathVariable Long id) {
        return facultyService.get(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> delete(@PathVariable Long id) {
        facultyService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public Faculty add(@RequestBody Faculty faculty) {
        return facultyService.add(faculty);
    }

    @PutMapping("{id}")
    public Faculty update(@PathVariable Long id, @RequestBody Faculty faculty) {
        return facultyService.update(id, faculty);
    }

    @GetMapping
    public List<Faculty> getByColor(String color) {
        return facultyService.getByColor(color);
    }

    @GetMapping(" getByNameOrColorIgnoreCase")
    public List<Faculty> getByNameOrColorIgnoreCase(
            @RequestParam String name,
            @RequestParam String color) {
        return facultyService.getByNameOrColorIgnoreCase(name, color);
    }

    @GetMapping("{id}/students")
    public List<Student> getStudents(@PathVariable long id) {
        return facultyService.getStudents(id);
    }

    @GetMapping("longestname")
    public String getLongestName(){
        return facultyService.getLongestName();
    }
}

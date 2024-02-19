package ru.hogwarts.school.service;

import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.io.IOException;
import java.util.List;
public interface StudentService {



     Student add(Student student);

    Student get(Long id);

    Student update(Long id, Student student);

    void delete(Long id);

    List<Student> getByAge(int age);

    List<Student> findByAgeBetween(int ageFrom, int ageTo);


    Faculty getFaculty(Long id);

    int getStudentCount();
    int getAvgYears();
    List<Student> getLastFive();



}

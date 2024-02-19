package ru.hogwarts.school.service.impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {



    private final StudentRepository studentRepository;




    public StudentServiceImpl(StudentRepository studentRepository, AvatarRepository avatarRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student add(Student student) {
        studentRepository.save(student);
        return student;

    }

    @Override
    public Student get(Long id) {
       return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student update(Long id, Student student) {
        return studentRepository.findById(id).map(studentFromDb -> {
            studentFromDb.setName(student.getName());
            studentFromDb.setAge(student.getAge());
            return studentRepository.save(studentFromDb);
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);

    }

    @Override
    public List<Student> getByAge(int age) {
        return studentRepository.findAll().stream()
                .filter(it->it.getAge()==age)
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> findByAgeBetween(int ageFrom, int ageTo) {
        return studentRepository.findByAgeBetween(ageFrom, ageTo);
    }

    @Override
    public Faculty getFaculty(Long id) {
        return studentRepository.findById(id)
                .map(Student::getFaculty).orElse(null);
    }

    public int getStudentCount() {
        return studentRepository.getStudentCount();
    }

    public int getAvgYears() {
        return studentRepository.getAvgYears();
    }
    public List<Student> getLastFive() {
        return studentRepository.getLastFive();
    }



}

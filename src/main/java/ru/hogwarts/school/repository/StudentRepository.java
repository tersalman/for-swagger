package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Student;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long> {

    Student getById(Long id);

    void deleteById(Long id);

    Optional<Student> findById(Long id);

}

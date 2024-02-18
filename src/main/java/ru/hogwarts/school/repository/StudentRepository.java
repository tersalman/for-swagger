package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long> {

    Student getById(Long id);

    void deleteById(Long id);

    Optional<Student> findById(Long id);

    List<Student> findByAgeBetween(int ageFrom, int ageTo);
@Query(value = "select count(*) from student;", nativeQuery = true)
    int getStudentCount();
@Query(value = "select  avg(age) from student;", nativeQuery = true)
    int getAvgYears();
@Query(value = "select * from student order by id desc limit 5;", nativeQuery = true)
    List<Student> getLastFive();


}

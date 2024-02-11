package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Faculty;

import java.util.List;
import java.util.Optional;

public interface FacultyRepository extends JpaRepository<Faculty,Long> {

    Faculty getById(Long id);

    void deleteById(Long id);

    Optional<Faculty> findById(Long id);

    List<Faculty> findByNameIgnoreCaseOrColorIgnoreCase(String name, String color);

}

package ru.hogwarts.school.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty add(Faculty faculty) {
        facultyRepository.save(faculty);
        return faculty;

    }

    @Override
    public Faculty get(Long id) {
        return facultyRepository.findById(id).orElse(null);
    }

    @Override
    public Faculty update(Long id, Faculty faculty) {
        return facultyRepository.findById(id).map(facultyFromDb -> {
            facultyFromDb.setName(faculty.getName());
            facultyFromDb.setColor(faculty.getColor());
            return facultyRepository.save(facultyFromDb);
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        facultyRepository.deleteById(id);

    }

    @Override
    public List<Faculty> getByColor(String color) {
        return facultyRepository.findAll().stream()
                .filter(it->it.getColor().equals(color))
                .collect(Collectors.toList());
    }
}

package ru.hogwarts.school.service.impl;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FacultyServiceImpl implements FacultyService {
    private Long idMaker = 0L;

    Map<Long, Faculty> faculties = new HashMap<>();


    @Override
    public Faculty add(Faculty faculty) {
        ++idMaker;
        faculty.setId(idMaker);
        faculties.put(idMaker, faculty);
        return faculty;

    }

    @Override
    public Faculty get(Long id) {
        return faculties.get(id);
    }

    @Override
    public Faculty update(Long id, Faculty faculty) {
        Faculty facultyFromStorage = faculties.get(id);
        facultyFromStorage.setName(faculty.getName());
        facultyFromStorage.setColor(faculty.getColor());
        return facultyFromStorage;
    }

    @Override
    public Faculty delete(Long id) {
        return faculties.remove(id);

    }

    @Override
    public List<Faculty> getByColor(String color) {
        return faculties.values().stream()
                .filter(it-> it.getColor().equals(color))
                .collect(Collectors.toList());
    }
}

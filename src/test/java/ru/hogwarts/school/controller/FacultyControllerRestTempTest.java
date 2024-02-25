package ru.hogwarts.school.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;

@SpringBootTest(webEnvironment = RANDOM_PORT )
public class FacultyControllerRestTempTest {
    @LocalServerPort
    private int port;
    @Autowired
    private FacultyRepository facultyRepository;

@Autowired
    private TestRestTemplate testRestTemplate;
@Test
    void shouldCreateFaculty() {
        Faculty faculty = new Faculty("Griffindor", "Red");

        ResponseEntity<Faculty> facultyResponseEntity = testRestTemplate.postForEntity(
                "http://localhost:" + port + "/faculty",
                faculty,
                Faculty.class
        );

        Assertions.assertNotNull(facultyResponseEntity);
        Assertions.assertEquals(facultyResponseEntity.getStatusCode(), HttpStatusCode.valueOf(200));

        Faculty actualFaculty = facultyResponseEntity.getBody();
        Assertions.assertNotNull(actualFaculty.getId());
        Assertions.assertEquals(actualFaculty.getName(),faculty.getName());
        Assertions.assertEquals(actualFaculty.getColor(),faculty.getColor());

    }

@Test
    void shouldUpdateFaculty() {
        Faculty faculty = new Faculty("Griffindor", "Red");
        facultyRepository.save(faculty);

        Faculty newFaculty = new Faculty("GriffindorHi", "RedHi");


        HttpEntity<Faculty> entity = new HttpEntity<Faculty>(newFaculty);
        ResponseEntity<Faculty> facultyResponseEntity = testRestTemplate.exchange(
                "http://localhost:" + port + "/faculty/" +faculty.getId(),
                HttpMethod.PUT,
                entity,
                Faculty.class
        );

        Assertions.assertNotNull(facultyResponseEntity);
        Assertions.assertEquals(facultyResponseEntity.getStatusCode(), HttpStatusCode.valueOf(200));


        Faculty actualFaculty = facultyResponseEntity.getBody();
        Assertions.assertEquals(actualFaculty.getName(),newFaculty.getName());
        Assertions.assertEquals(actualFaculty.getColor(),newFaculty.getColor());


    }

@Test
    void shouldGetFaculty() {
        Faculty faculty = new Faculty("Griffindor", "Red");
        faculty = facultyRepository.save(faculty);

        ResponseEntity<Faculty> facultyResponseEntity = testRestTemplate.getForEntity(
                "http://localhost:" + port + "/faculty/"+faculty.getId(),
                Faculty.class
        );
        Assertions.assertNotNull(facultyResponseEntity);
        Assertions.assertEquals(facultyResponseEntity.getStatusCode(), HttpStatusCode.valueOf(200));

        Faculty actualFaculty = facultyResponseEntity.getBody();
        Assertions.assertNotNull(actualFaculty.getId());
        Assertions.assertEquals(actualFaculty.getName(),faculty.getName());
        Assertions.assertEquals(actualFaculty.getColor(),faculty.getColor());
    }

@Test
    void shouldDeleteFaculty() {
        Faculty faculty = new Faculty("Griffindor", "Red");
        faculty = facultyRepository.save(faculty);

        ResponseEntity<Faculty> facultyResponseEntity = testRestTemplate.exchange(
                "http://localhost:" + port + "/faculty/" +faculty.getId(),
                HttpMethod.DELETE,
                null,
                Faculty.class
        );
        Assertions.assertNotNull(facultyResponseEntity);
        Assertions.assertEquals(facultyResponseEntity.getStatusCode(), HttpStatusCode.valueOf(200));

    }





}

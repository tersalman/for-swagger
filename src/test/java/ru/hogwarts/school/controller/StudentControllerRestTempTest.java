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
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT )
public class StudentControllerRestTempTest {

    @LocalServerPort
    private int port;
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TestRestTemplate testRestTemplate;
    @Test
    void shouldCreateStudent() {
        Student student = new Student("Griffindor", 1);

        ResponseEntity<Student> studentResponseEntity = testRestTemplate.postForEntity(
                "http://localhost:" + port + "/student",
                student,
                Student.class
        );

        Assertions.assertNotNull(studentResponseEntity);
        Assertions.assertEquals(studentResponseEntity.getStatusCode(), HttpStatusCode.valueOf(200));

        Student actualStudent = studentResponseEntity.getBody();
        Assertions.assertNotNull(actualStudent.getId());
        Assertions.assertEquals(actualStudent.getName(),student.getName());
        Assertions.assertEquals(actualStudent.getAge(),student.getAge());

    }

    @Test
    void shouldUpdateStudent() {
        Student student = new Student("Griffindor", 1);
        studentRepository.save(student);

        Student newStudent = new Student("GriffindorHi", 2);


        HttpEntity<Student> entity = new HttpEntity<Student>(newStudent);
        ResponseEntity<Student> studentResponseEntity = testRestTemplate.exchange(
                "http://localhost:" + port + "/student/" +student.getId(),
                HttpMethod.PUT,
                entity,
                Student.class
        );

        Assertions.assertNotNull(studentResponseEntity);
        Assertions.assertEquals(studentResponseEntity.getStatusCode(), HttpStatusCode.valueOf(200));


        Student actualStudent = studentResponseEntity.getBody();
        Assertions.assertEquals(actualStudent.getName(),newStudent.getName());
        Assertions.assertEquals(actualStudent.getAge(),newStudent.getAge());


    }

    @Test
    void shouldGetStudent() {
        Student student = new Student("Griffindor", 1);
        student = studentRepository.save(student);

        ResponseEntity<Student> studentResponseEntity = testRestTemplate.getForEntity(
                "http://localhost:" + port + "/student/"+student.getId(),
                Student.class
        );
        Assertions.assertNotNull(studentResponseEntity);
        Assertions.assertEquals(studentResponseEntity.getStatusCode(), HttpStatusCode.valueOf(200));

        Student actualStudent = studentResponseEntity.getBody();
        Assertions.assertNotNull(actualStudent.getId());
        Assertions.assertEquals(actualStudent.getName(),student.getName());
        Assertions.assertEquals(actualStudent.getAge(),student.getAge());
    }

    @Test
    void shouldDeleteStudent() {
        Student student = new Student("Griffindor", 1);
        student = studentRepository.save(student);

        ResponseEntity<Student> studentResponseEntity = testRestTemplate.exchange(
                "http://localhost:" + port + "/student/" +student.getId(),
                HttpMethod.DELETE,
                null,
                Student.class
        );
        Assertions.assertNotNull(studentResponseEntity);
        Assertions.assertEquals(studentResponseEntity.getStatusCode(), HttpStatusCode.valueOf(200));

    }

}

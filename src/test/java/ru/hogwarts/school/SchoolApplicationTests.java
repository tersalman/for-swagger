package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SchoolApplicationTests {
	@LocalServerPort
	private int port;

	@Autowired
	private StudentController studentController;
	@Autowired
	private FacultyController facultyController;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void contextLoads() throws Exception {
		Assertions.assertThat(studentController).isNotNull();
		Assertions.assertThat(facultyController).isNotNull();
	}

	void getByIdInStudentTest() throws Exception {
		Assertions
				.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/{id}", Student.class))
				.isNotNull();
	}




}

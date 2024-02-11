package ru.hogwarts.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
public class StudentControllerWebMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
   private StudentRepository studentRepository;

    @MockBean
    private StudentService studentService;


    @Test
     void shouldAddStudent() throws Exception {
        Long studentId =1L;
        Student student = new Student("Ivan", 20);
        Student savedStudent = new Student("Ivan", 20);


//
        when(studentService.add(student)).thenReturn(savedStudent);

        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.post("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)));
        perform.andExpect(jsonPath("$.id").value(savedStudent.getId()));
        perform.andExpect(jsonPath("$.name").value(savedStudent.getName()));
        perform.andExpect(jsonPath("$.age").value(savedStudent.getAge()));
    }

    @Test
    void shouldGetStudent() throws Exception {
        Long id = 1L;
        Student student = new Student("Ivan", 20);


        when(studentService.get(id)).thenReturn(student);


        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.get("/student/{id}", id));

        perform
                .andExpect(jsonPath("$.name").value(student.getName()))
                .andExpect( jsonPath("$.age").value(student.getAge()));


    }
    @Test
    void shouldUpdateStudent() throws Exception {
        Long studentId = 1L;
        Student student = new Student("Ivan", 20);

        when(studentService.update(studentId,student)).thenReturn(student);

        ResultActions perform = mockMvc.perform
                (MockMvcRequestBuilders.put("/student/{id}",studentId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)));

        perform.andExpect(jsonPath("$.name").value(student.getName()));
        perform.andExpect(jsonPath("$.age").value(student.getAge()));
    }
    @Test
    void shouldDeleteStudent() throws Exception {
        Long studentId = 1L;
        Student student = new Student("Ivan", 20);

        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.delete("/student/{id}",studentId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)));

        perform.andExpect(status().isOk());
    }




}

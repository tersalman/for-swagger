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
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(FacultyController.class)
public class FacultyControllerWebMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private FacultyRepository facultyRepository;

    @MockBean
    private FacultyService facultyService;


    @Test
    void shouldAddFaculty() throws Exception {
        Long facultyId =1L;
        Faculty faculty = new Faculty("Griffindor", "Red");
        Faculty savedFaculty = new Faculty("Griffindor", "Red");


//
        when(facultyService.add(faculty)).thenReturn(savedFaculty);

        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.post("/faculty")
                .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(faculty)));
        perform.andExpect(jsonPath("$.id").value(savedFaculty.getId()));
        perform.andExpect(jsonPath("$.name").value(savedFaculty.getName()));
        perform.andExpect(jsonPath("$.color").value(savedFaculty.getColor()));
    }

    @Test
    void shouldGetFaculty() throws Exception {
        Long id = 1L;
        Faculty faculty = new Faculty("Griffindor", "Red");


        when(facultyService.get(id)).thenReturn(faculty);


        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.get("/faculty/{id}", id));

        perform
                .andExpect(jsonPath("$.name").value(faculty.getName()))
                .andExpect( jsonPath("$.color").value(faculty.getColor()));


    }
    @Test
    void shouldUpdateFaculty() throws Exception {
        Long facultyId = 1L;
        Faculty faculty = new Faculty("Griffindor", "Red");

        when(facultyService.update(facultyId,faculty)).thenReturn(faculty);

        ResultActions perform = mockMvc.perform
                (MockMvcRequestBuilders.put("/faculty/{id}",facultyId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(faculty)));

        perform.andExpect(jsonPath("$.name").value(faculty.getName()));
        perform.andExpect(jsonPath("$.color").value(faculty.getColor()));
    }
    @Test
    void shouldDeleteFaculty() throws Exception {
        Long facultyId = 1L;
        Faculty faculty = new Faculty("Griffindor", "Red");

        ResultActions perform = mockMvc.perform
                (MockMvcRequestBuilders.delete("/faculty/{id}",facultyId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(faculty)));

        perform.andExpect(status().isOk());
    }



}

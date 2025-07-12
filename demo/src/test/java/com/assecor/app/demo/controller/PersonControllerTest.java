package com.assecor.app.demo.controller;

import com.assecor.app.demo.dto.PersonCreateDto;
import com.assecor.app.demo.model.Person;
import com.assecor.app.demo.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PersonService personService;

    @Autowired
    private ObjectMapper objectMapper;

    private List<Person> mockPersons;

    @BeforeEach
    void setUp() {
        mockPersons = Arrays.asList(
                new Person(1, "Müller", "Hans", "67742 Lauterecken", 1),
                new Person(2, "Petersen", "Peter", "18439 Stralsund", 2),
                new Person(3, "Johnson", "Johnny", "88888 made up", 3)
        );
    }

    @Test
    void testGetAllPersons() throws Exception {
        when(personService.getAllPersons()).thenReturn(mockPersons);

        mockMvc.perform(get("/persons")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].firstname", is("Hans")))
                .andExpect(jsonPath("$[1].color", is("grün")));
    }

    @Test
    void testGetPersonById() throws Exception {
        when(personService.getPersonById(1)).thenReturn(Optional.of(mockPersons.getFirst()));

        mockMvc.perform(get("/persons/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstname", is("Hans")))
                .andExpect(jsonPath("$.lastname", is("Müller")));
    }

    @Test
    void testGetPersonByIdNotFound() throws Exception {
        when(personService.getPersonById(99)).thenReturn(Optional.empty());

        mockMvc.perform(get("/persons/99")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("Person with ID 99 not found")));
    }

    @Test
    void testGetPersonsByColorFound() throws Exception {
        List<Person> bluePersons = Arrays.asList(mockPersons.getFirst());
        when(personService.getPersonsByColor(1)).thenReturn(bluePersons);

        mockMvc.perform(get("/persons/color/blau")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstname", is("Hans")))
                .andExpect(jsonPath("$[0].color", is("blau")));
    }

    @Test
    void testGetPersonsByColorUnknownColor() throws Exception {
        mockMvc.perform(get("/persons/color/unknown")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath(
                        "$.message",
                        is("Color (unknown) is invalid. Valid colors: [blau, grün, violett, rot, gelb, türkis, weiß]")
                ));
    }

    @Test
    void testAddPersonSuccess() throws Exception {
        PersonCreateDto createDto = new PersonCreateDto("Mustermann", "Max", "12345 Musterstadt", "rot");
        Person createdPerson = new Person(4, "Mustermann", "Max", "12345 Musterstadt", 4);

        when(personService.createPerson(any(Person.class))).thenReturn(createdPerson);

        mockMvc.perform(post("/persons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(4)))
                .andExpect(jsonPath("$.firstname", is("Max")))
                .andExpect(jsonPath("$.color", is("rot")));
    }
}

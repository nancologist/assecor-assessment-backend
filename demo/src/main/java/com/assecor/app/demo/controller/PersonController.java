package com.assecor.app.demo.controller;

import com.assecor.app.demo.dto.PersonCreateDto;
import com.assecor.app.demo.dto.PersonDto;
import com.assecor.app.demo.model.ColorLookup;
import com.assecor.app.demo.model.Person;
import com.assecor.app.demo.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("persons")
public class PersonController {
    private final Function<Person, PersonDto> convertPersonToDto = person -> new PersonDto(
            person.getId(),
            person.getLastname(),
            person.getFirstname(),
            person.getAddress(),
            ColorLookup.getColorById(person.getColorId()));

    @Autowired
    private PersonService personService;

    @GetMapping()
    public ResponseEntity<List<PersonDto>> getAll() {
        List<PersonDto> personDtos = this.personService.getAllPersons()
                .stream().map(convertPersonToDto).collect(Collectors.toList());
        return ResponseEntity.ok(personDtos);
    }

    @GetMapping("{id}")
    public ResponseEntity<PersonDto> getById(@PathVariable Long id) {
        Optional<PersonDto> personDto = this.personService.getPersonById(id).map(convertPersonToDto);
        return personDto.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("color/{color}")
    public ResponseEntity<List<PersonDto>> getByColor(@PathVariable String color) {
        Optional<Integer> colorId = ColorLookup.getIdFromColor(color);
        if (colorId.isPresent()) {
            List<PersonDto> personDtos = this.personService.getPersonsByColor(colorId.get())
                    .stream().map(convertPersonToDto).collect(Collectors.toList());
            return ResponseEntity.ok(personDtos);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

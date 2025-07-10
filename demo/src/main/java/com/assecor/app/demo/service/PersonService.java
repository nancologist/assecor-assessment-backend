package com.assecor.app.demo.service;

import com.assecor.app.demo.dto.PersonCreateDto;
import com.assecor.app.demo.model.Person;
import com.assecor.app.demo.repository.IRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final IRepository<Person> personRepository;

    public PersonService(IRepository<Person> personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPersonsByColor(int colorId) {
        return personRepository.findByColor(colorId);
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Optional<Person> getPersonById(long id) {
        return personRepository.findById(id);
    }
}

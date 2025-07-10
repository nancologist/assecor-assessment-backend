package com.assecor.app.demo.repository;

import com.assecor.app.demo.CsvDataSource;
import com.assecor.app.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PersonRepository implements IRepository<Person> {
    private final CsvDataSource dataSource;

    public PersonRepository(CsvDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Person> findAll() {
        return this.dataSource.persons;
    }

    @Override
    public List<Person> findByColor(int colorId) {
        return this.dataSource.persons.stream()
                .filter(person -> person.getColorId() == colorId)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Person> findById(long id) {
        return this.dataSource.persons.stream().filter(p -> p.getId() == id).findFirst();
    }
}

package com.assecor.app.demo;

import com.assecor.app.demo.model.Person;
import jakarta.annotation.PostConstruct;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvDataSource {
    private static final String PATH = "src/main/resources/sample-input.csv";
    public final List<Person> persons = new ArrayList<>();
    private static long generatedID = 1;

    @PostConstruct
    public void loadData() {
        try (java.io.Reader reader = new FileReader(PATH)) {
            for (CSVRecord record : getRecords(reader)) {
                Person person = new Person(
                        generatedID,
                        record.get("lastname").trim(),
                        record.get("firstname").trim(),
                        record.get("address").trim(),
                        Integer.parseInt(record.get("colorId").trim())
                );
                persons.add(person);
                generatedID++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Person insert(Person person) {
        person.setId(generatedID);
        persons.add(person);
        generatedID++;
        return person;
    }

    private static CSVParser getRecords(java.io.Reader reader) throws IOException {
        return CSVFormat
                .Builder
                .create()
                .setHeader("lastname", "firstname", "address", "colorId")
                .get()
                .parse(reader);
    }
}

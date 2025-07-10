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
public class DataSource {
    private static final String PATH = "src/main/resources/sample-input.csv";
    public final List<Person> persons = new ArrayList<>();

    @PostConstruct
    public void loadData(String[] args) {
        try (java.io.Reader reader = new FileReader(PATH)) {
            long id = 1;
            for (CSVRecord record : getRecords(reader)) {
                Person person = new Person(
                        id,
                        record.get("lastname"),
                        record.get("firstname"),
                        record.get("address"),
                        Integer.parseInt(record.get("colorId"))
                );
                persons.add(person);
                id++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

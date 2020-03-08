package com.sample.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggingController {

    private static Logger LOGGER = LoggerFactory.getLogger(LoggingController.class);

    private RandomPersonGenerator generator;

    public LoggingController(RandomPersonGenerator generator) {
        this.generator = generator;
    }

    @GetMapping("/person")
    public void generatePerson() {
        Person person = generator.generate();
        LOGGER.info("Person: Name=[{}] City=[{}] Age=[{}]", person.getName(), person.getCity(), person.getAge());
    }

    @GetMapping("/exception")
    public void generateException() {
        Person person = generator.generate();
        LOGGER.error("Error while generating person: ", new RuntimeException(String.format("Name=[%s]", person.getName())));
    }
}

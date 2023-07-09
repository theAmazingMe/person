package com.jean.controllers;

import com.busi.person.domain.Person;
import com.busi.person.domain.ProposePerson;
import com.jean.services.DomainService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.List;

@RequestMapping("/people")
@RestController
public class PersonController {

    @Autowired
    private DataSource dataSource;

    private DomainService service;

    @PostConstruct
    void init(){
        this.service = new DomainService(dataSource);
    }

    @GetMapping
    public ResponseEntity<List<Person>> fetchAll(){
        return ResponseEntity.ok(service.fetchAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> fetchOneById(@PathVariable long id){
        return ResponseEntity.ok(service.fetchOne(id));
    }

    @PostMapping
    public ResponseEntity saveOne(@RequestBody ProposePerson person){
        return ResponseEntity.ok(service.save(person));
    }

    @PutMapping("/{id}")
    public ResponseEntity editOne(@RequestBody ProposePerson person,@PathVariable long id){
        return ResponseEntity.ok(service.edit(id,person));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOne(@PathVariable long id){
        return ResponseEntity.ok(service.remove(id));
    }
}

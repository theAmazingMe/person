package com.jean.services;

import com.busi.person.domain.Person;
import com.busi.person.domain.PersonRepository;
import com.busi.person.domain.ProposePerson;
import com.jean.dao.PersonDAO;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
public class DomainService implements PersonRepository {

    PersonDAO repository;

    public DomainService(DataSource dataSource){
        repository = new PersonDAO(dataSource);
    }

    @Override
    public List<Person> fetchAll() {
        return repository.fetchAll();
    }

    @Override
    public Person fetchOne(Long id) {
        return repository.fetchOne(id);
    }

    @Override
    public int save(ProposePerson person) {
        return repository.save(person);
    }

    @Override
    public int edit(long id, ProposePerson person) {
        return repository.edit(id,person);
    }

    @Override
    public int remove(long id) {
        return repository.remove(id);
    }
}

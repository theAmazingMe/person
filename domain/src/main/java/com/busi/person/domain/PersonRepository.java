package com.busi.person.domain;

import java.util.List;

public interface PersonRepository {
    List<Person> fetchAll();

    Person fetchOne(Long id);

    int save(ProposePerson person);

    int edit(long id, ProposePerson person);

    int remove(long id);
}

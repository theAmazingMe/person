package com.jean.services;

import com.busi.person.domain.Person;
import com.busi.person.domain.PersonRepository;
import com.busi.person.domain.ProposePerson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles(value = "test")
@SpringBootTest
@SqlGroup({
        @Sql(value = "classpath:jdbc/schema.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql("classpath:jdbc/test-data.sql"),
        @Sql(value = "classpath:jdbc/teardown.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
class DomainServiceTest {

    private PersonRepository service;

    @Autowired
    DomainServiceTest(PersonRepository service){
        this.service = service;
    }

    @Test
    void itGivesMultipleRecords() {
        assertNotNull(service.fetchAll());
    }

    @Test
    void itFetchesOnePerson() {
        assertNotNull(service.fetchOne(1L));
    }

    @Test
    void itGivesAPositiveNumberOfRecord() {
        assertNotEquals(0,service.fetchAll().size());
    }

    @Test
    void itSavesOneNewPerson(){
        int initialCount = service.fetchAll().size();

        service.save(new ProposePerson(
                "Collins",
                "Marshall",
                LocalDateTime.of(1986, Month.APRIL,05,0,0)));

        assertNotEquals(initialCount, service.fetchAll().size());
    }

    @Test
    void itSavesTwoPersons(){
        int initialCount = service.fetchAll().size();

        service.save(new ProposePerson(
                "Collins",
                "Marshall",
                LocalDateTime.of(1986, Month.APRIL,05,0,0)));

        service.save(new ProposePerson(
                "Marcelin",
                "Pain et vin",
                LocalDateTime.of(1987, Month.MARCH,05,0,0)));

        assertEquals(initialCount+2, service.fetchAll().size());
    }

    @Test
    void itEditsOnePerson() {
        long id = 1L;
        String originalFirstName = service.fetchOne(id).firstname();

        service.edit(id, new ProposePerson(
                "Hocus",
                "Pocus",
                LocalDateTime.of(1993, Month.JANUARY,01,0,0)));

        assertNotEquals(originalFirstName,service.fetchOne(id).firstname());
    }

    @Test
    void itDeletesOnePerson(){
        long id = 1L;
        service.remove(id);

        assertThrows(EmptyResultDataAccessException.class,() -> {
            assertNull(service.fetchOne(id));
        });
    }

    @Test
    void itDeletesExactlyOnePerson(){

        List<Person> list = service.fetchAll();
        int initialCount = list.size();

        service.remove(list.get(0).id());

        assertEquals(initialCount-1,service.fetchAll().size());
    }
}
package com.jean.controllers;

import com.busi.person.domain.Person;
import com.busi.person.domain.ProposePerson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static com.jean.helper.URLTemplatePerformer.*;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles(value = "test")
@SpringBootTest
@SqlGroup({
        @Sql(value = "classpath:jdbc/schema.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql("classpath:jdbc/test-data.sql"),
        @Sql(value = "classpath:jdbc/teardown.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
@AutoConfigureMockMvc
class PersonControllerTest {
    private final MockMvc mockMvc;

    private static final String ONE_PERSON_S_URL_TEMPLATE = "/people/1";
    private static final String WHOLE_PEOPLE_URL_TEMPLATE = "/people";

    @Autowired
    public PersonControllerTest(MockMvc mockMvc){
        this.mockMvc = mockMvc;
    }

    @Test
    void itFetchesAllPeople() throws Exception {
        List response = get(List.class,WHOLE_PEOPLE_URL_TEMPLATE,mockMvc);

        assertEquals(3, response.size());
    }

    @Test
    void itFetchesOnePersonWithAnAge() throws Exception {

        Person person = get(Person.class,ONE_PERSON_S_URL_TEMPLATE,mockMvc);

        assertNotEquals(0,person.age());
    }

    @Test
    void itSavesSomeone() throws Exception {
        // Given
        int initialCount = get(List.class,WHOLE_PEOPLE_URL_TEMPLATE,mockMvc).size();

        LocalDateTime birthday = LocalDateTime.of(1993, Month.JANUARY,01,01,0);
        ProposePerson person = new ProposePerson("Manu", "VERHANNEMAN", birthday);

        // When
        post(person,WHOLE_PEOPLE_URL_TEMPLATE,mockMvc);

        // Then
        int secondCount = get(List.class,WHOLE_PEOPLE_URL_TEMPLATE,mockMvc).size();
        assertTrue(initialCount < secondCount);
    }

    @Test
    void itEditsSomeone() throws Exception {
        // Given
        String newFirstName = "Alain";
        Person fetched = get(Person.class,ONE_PERSON_S_URL_TEMPLATE,mockMvc);
        ProposePerson modified = new ProposePerson(newFirstName, fetched.lastname(), fetched.birthday());

        // When
        put(modified,ONE_PERSON_S_URL_TEMPLATE,mockMvc);

        // Then
        Person reFetched = get(Person.class,ONE_PERSON_S_URL_TEMPLATE,mockMvc);
        assertEquals(newFirstName,reFetched.firstname());
    }

    @Test
    void itDeletesSomeone() throws Exception {
        // Given
        int initialCount = get(List.class,WHOLE_PEOPLE_URL_TEMPLATE,mockMvc).size();

        // When
        delete(ONE_PERSON_S_URL_TEMPLATE,mockMvc);

        // Then
        int secondCount = get(List.class,WHOLE_PEOPLE_URL_TEMPLATE,mockMvc).size();
        assertTrue(initialCount > secondCount);
    }
}

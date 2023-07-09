package com.jean.dao;

import com.busi.person.domain.Person;
import com.busi.person.domain.PersonRepository;
import com.busi.person.domain.ProposePerson;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class PersonDAO implements PersonRepository {

    public PersonDAO(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    private final JdbcTemplate jdbcTemplate;


    @Override
    public List<Person> fetchAll() {
        return jdbcTemplate.query("SELECT * FROM public.Person", new PersonRowMapper());
    }

    @Override
    public Person fetchOne(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM public.Person WHERE id = ? ", new PersonRowMapper() , id);
    }

    @Override
    public int save(ProposePerson person) {
        return jdbcTemplate.update(
                "INSERT INTO public.Person VALUES (DEFAULT,?, ?, ?)", person.lastname(), person.firstname(), person.birthday());
    }

    @Override
    public int edit(long id, ProposePerson person) {
        return jdbcTemplate.update(
                "UPDATE public.Person SET firstname=?, lastname=?, birthday=? WHERE id =?",
                person.firstname(),person.lastname(),person.birthday(),id);
    }

    @Override
    public int remove(long id) {
        return jdbcTemplate.update("DELETE FROM public.Person WHERE id=?",id);
    }
}

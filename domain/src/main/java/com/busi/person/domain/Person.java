package com.busi.person.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public record Person(Long id,
                     String firstname,
                     String lastname,
                     LocalDateTime birthday) {

    public Person(String firstname, String lastname, LocalDateTime birthday){
        this(null,firstname,lastname,birthday);
    }

    public int age (){
        return AgeCalculator.ageUpToNow(birthday.toLocalDate());
    }

    private static class AgeCalculator {

        public static int ageUpToNow(LocalDate birthDate) {
            if (birthDate == null) {
                return 0;
            }
            return Period.between(birthDate, LocalDate.now()).getYears();
        }
    }
}

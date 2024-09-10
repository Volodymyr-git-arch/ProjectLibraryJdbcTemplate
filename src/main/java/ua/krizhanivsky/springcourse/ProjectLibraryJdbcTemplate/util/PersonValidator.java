package ua.krizhanivsky.springcourse.ProjectLibraryJdbcTemplate.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.krizhanivsky.springcourse.ProjectLibraryJdbcTemplate.dao.PersonDAO;
import ua.krizhanivsky.springcourse.ProjectLibraryJdbcTemplate.models.Person;


@Component
public class PersonValidator implements Validator {

    private final PersonDAO personDAO;

    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;
        if (personDAO.getPersonByFullName(person.getFullName()).isPresent())
            errors.rejectValue("fullName", "", "A person with this full name already exists");

    }


}

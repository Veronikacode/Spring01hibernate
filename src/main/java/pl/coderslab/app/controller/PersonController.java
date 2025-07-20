package pl.coderslab.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.app.dao.PersonDao;
import pl.coderslab.app.entity.Person;
import pl.coderslab.app.entity.PersonDetails;

@Controller
@RequestMapping("/person")
@ResponseBody
public class PersonController {

    private final PersonDao personDao;

    public PersonController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @PostMapping("/create")
    public String create(@RequestParam String login,
                         @RequestParam String password,
                         @RequestParam String email,
                         @RequestParam String firstName,
                         @RequestParam String lastName,
                         @RequestParam String streetNumber,
                         @RequestParam String street,
                         @RequestParam String city) {
        PersonDetails personDetails = new PersonDetails();
        personDetails.setFirstName(firstName);
        personDetails.setLastName(lastName);
        personDetails.setStreetNumber(streetNumber);
        personDetails.setStreet(street);
        personDetails.setCity(city);

        Person person = new Person();
        person.setLogin(login);
        person.setPassword(password);
        person.setEmail(email);
        person.setPersonDetails(personDetails);

        personDao.save(person);
        return "Dodano osobę o ID: " + person.getId();
    }

    @GetMapping("/read")
    public String read(@RequestParam Long id) {
        Person person = personDao.findById(id);
        if (person == null) {
            return "Nie znaleziono osoby o ID: " + id;
        }
        return person.toString();
    }

    @PostMapping("/update")
    public String update(@RequestParam Long id, @RequestParam String login, @RequestParam String password, @RequestParam String email) {
        Person person = personDao.findById(id);
        if (person == null) {
            return "Nie znaleziono osoby o ID: " + id;
        }
        person.setLogin(login);
        person.setPassword(password);
        person.setEmail(email);
        personDao.update(person);
        return "Zaktualizowano osobę o ID: " + id;
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id) {
        personDao.delete(id);
        return "Usunięto osobę o ID: " + id + " (jeśli istniała)";
    }
}

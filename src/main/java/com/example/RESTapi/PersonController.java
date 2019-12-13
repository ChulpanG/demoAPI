package com.example.RESTapi;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Person;
import com.example.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "person")
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    @GetMapping()
    public @ResponseBody Iterable<Person> getAllPerson() {
        return personRepository.findAll();
    }

    @GetMapping("{personid}")
    public ResponseEntity<Person> getPersonById(@PathVariable(value = "personid") long personId)
            throws ResourceNotFoundException {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: " + personId));
        return ResponseEntity.ok().body(person);
    }

    @PostMapping()
    public Person createPerson(@Valid @RequestBody Person person){
        return personRepository.save(person);
    }
	/*@PostMapping("/add")
	public @ResponseBody String createPerson(@RequestParam String name, @RequestParam String surname,
											 @RequestParam String position, @RequestParam String birthdate,
											 @RequestParam String gender, @RequestParam int personalcode) {
		Person person = new Person();
		person.setName(name);
		person.setSurname(surname);
		person.setPosition(position);
		person.setBirthdate(birthdate);
		person.setGender(gender);
		person.setPersonalcode(personalcode);
		personRepository.save(person);
		return "Saved";
	}*/

    @PutMapping("{personid}")
    public ResponseEntity<Person> updatePerson(@PathVariable(value = "personid") Long personId,
                                               @Valid @RequestBody Person personDetails) throws ResourceNotFoundException {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: " + personId));

        person.setPersonID(personDetails.getPersonID());
        person.setSurname(personDetails.getSurname());
        person.setName(personDetails.getName());
        person.setPosition(personDetails.getPosition());
        person.setGender(personDetails.getGender());
        person.setBirthdate(personDetails.getBirthdate());
        person.setPersonalcode(personDetails.getPersonalcode());

        final Person updatedPerson = personRepository.save(person);
        return ResponseEntity.ok(updatedPerson);
    }

    @DeleteMapping("{personid}")
    public Map<String, Boolean> deletePerson(@PathVariable(value = "personid") Long personId)
            throws ResourceNotFoundException {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + personId));

        personRepository.delete(person);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
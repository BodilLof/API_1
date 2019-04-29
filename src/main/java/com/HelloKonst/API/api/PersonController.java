package com.HelloKonst.API.api;

import com.HelloKonst.API.model.Person;
import com.HelloKonst.API.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;


@RequestMapping("api/v1/person") //definierar path endpoint som används i anropet
@RestController  // klassen är REST, kan exponera endpoints för client
public class PersonController {

    private final PersonService personService;

    @Autowired //spring injects servicen i konstruktorn. Sk dependency injection.
    public PersonController(PersonService personService){
        this.personService = personService;
    }
    //metoden används som POST-request
    @PostMapping
    public void addPerson(@Valid @NotNull @RequestBody Person person){  // @RequestBody -> payload fr Json body
        personService.addPerson(person);               //som används i addPeron
    }

    @GetMapping //metoden används som GET-request
    public List<Person> getAllPeople(){
        return personService.getAllPeople();
    }

    @GetMapping(path = "{id}") //idt som anges av clienten görs om till UUID
    public Person getPersonById(@PathVariable("id") UUID id){  //clienten kan ange personens id i GETrequest
        return personService.getPersonById(id)
                .orElse(null); //Här kan man returnera meddelande
    }

    @DeleteMapping(path = "{id}")
    public void deletePersonById(@PathVariable("id") UUID id){
        personService.deletePerson(id);
    }

    @PutMapping (path = "{id}")
    public void updatePerson(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody Person personToUpdate){  //tar en person från request body (json)
        personService.updatePerson(id, personToUpdate);

    }

}

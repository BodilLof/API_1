package com.HelloKonst.API.service;

import com.HelloKonst.API.dao.PersonDao;
import com.HelloKonst.API.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//denna serviceklass ska innehålla all den logik som applikationen kräver

@Service //Springnotation  dependency injection som kopplar ihop person och personDao
public class PersonService {

    private final PersonDao personDao;

    @Autowired //Springnotation för injection i konstruktorn
    public PersonService(@Qualifier("fakeDao")PersonDao personDao){ //fakeDao identifierar db kan ändras till SQL
        this.personDao = personDao;
    }

    public int addPerson(Person person){
        return personDao.insertPerson(person);
    }

    public List<Person> getAllPeople(){
        return personDao.selectAllPeople();
    }

    public Optional <Person> getPersonById(UUID id){
        return personDao.selectPersonById(id);
    }

    public int deletePerson(UUID id){
        return personDao.deletePersonById(id);

    }

    public int updatePerson(UUID id, Person newPerson){
        return personDao.updatePersonById(id, newPerson);
    }

}

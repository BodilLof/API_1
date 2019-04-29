package com.HelloKonst.API.dao;

import com.HelloKonst.API.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//klass som fakar databasen - Här är DB en arraylist med personobjekt som har id och namn

@Repository("fakeDao")// Springnotation som gör att klassen kan injectas - skapar beroendet. fakeDao identifierar databasen, se personService
public class FakePersonDataAcessService implements PersonDao {

    private static List <Person> DB =new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName())); //här ska sql in istället för tillägg i listan. Eller direkt i PersonDao-klassen?
        return 1;                                 ////INSERT INTO Person (id, name)
    }                                             ////VALUES (name, id)


    @Override
    public List <Person> selectAllPeople(){
        return DB;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return DB.stream() //söka upp personen i DB med streams
               .filter(person -> person.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> personMaybe = selectPersonById(id); //finns personen i db? Då tas den bort.
        if (personMaybe.isEmpty()){
            return 0; //kasta exception?
        }
        DB.remove(personMaybe.get());
        return 1;
    }

    @Override
    public int updatePersonById(UUID id, Person update) {
        return selectPersonById(id)
                .map(person -> {
                    int indexOfPersonToUpdate = DB.indexOf(person);
                    if (indexOfPersonToUpdate >=0) { //personen hittad om större eller lika med 0
                         DB.set(indexOfPersonToUpdate, new Person (id, update.getName()));
                         return 1;
                    }
                    return 0;
                })
                .orElse(0); //personen finns inte - returnerar 0
    }
}

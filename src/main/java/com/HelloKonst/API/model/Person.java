package com.HelloKonst.API.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class Person {
    private final UUID id;

    @NotBlank
    private final String name;

    //översätta Jsonobjektet till javaobjektets properties
    public Person(@JsonProperty("id")UUID id, //id genereras här då det behöver inte postas fr client
                  @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
package org.ijovic.demo.neo4j.entities;

import org.ijovic.demo.neo4j.domain.Gender;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ivanj on 11.06.2017.
 */
@NodeEntity(label = "DemoPerson")
public class PersonEntity implements Serializable {

    @GraphId
    private Long id; //must be Long but not long

    private String firstName;
    private String lastName;
    private String gender;

    @Relationship(type = "SIBLING", direction = Relationship.UNDIRECTED)
    public Set<PersonEntity> siblings;

    @Relationship(type = "PARENT", direction = Relationship.INCOMING)
    public Set<PersonEntity> parents;


    public PersonEntity() {
    }

    public PersonEntity(String firstName, String lastName, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void createRelation(PersonEntity person, String type){
        if(type.equalsIgnoreCase("PARENT")){
            if(parents == null){
                parents = new HashSet<>();
            }
            parents.add(person);
        }

        if(type.equalsIgnoreCase("SIBLING")){
            if(siblings == null){
                siblings = new HashSet<>();
            }
            siblings.add(person);
        }
    }
}

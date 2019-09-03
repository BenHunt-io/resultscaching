package com.benhunt.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.benhunt.model.Person;

@Stateless
public class PersonRepository {

    @PersistenceContext(unitName="sandbox-pu")
    EntityManager em;
    
    
    public List<String> getPersonsByLastName(String lastName){
    	TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p WHERE p.lastName = :lastName", Person.class);
    	query.setParameter("lastName", lastName);
    	return query.getResultList().stream().map(Person::getFirstName).collect(Collectors.toList());
    }
    
    public List<String> getPersonsByLastNameCached(String lastName){
    	TypedQuery<Person> query = em.createNamedQuery("Person.getPersonsByLastName", Person.class);
    	query.setParameter("lastName", lastName);
    	return query.setMaxResults(1).getResultList().stream().map(Person::getFirstName).collect(Collectors.toList());
    }
    
    public void insertPerson(String firstName, String lastName, BigInteger age) {
    	Person person = new Person();
    	person.setFirstName(firstName);
    	person.setLastName(lastName);
    	person.setAge(age);
    	em.persist(person);
    }
}

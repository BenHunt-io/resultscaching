package com.benhunt.model;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;
import javax.persistence.Table;

import org.eclipse.persistence.config.QueryHints;

@Entity
@Table
@NamedQueries(
	@NamedQuery(name="Person.getPersonsByLastName", query = "SELECT p FROM Person p WHERE p.lastName = :lastName",
		hints = { 
				@QueryHint(name=QueryHints.QUERY_RESULTS_CACHE, value="true"),
				@QueryHint(name=QueryHints.QUERY_RESULTS_CACHE_SIZE, value="500") 
		}))
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	BigInteger personID;
	String firstName;
	String lastName;
	BigInteger age;

	public BigInteger getPersonID() {
		return personID;
	}
	public void setPersonID(BigInteger personID) {
		this.personID = personID;
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
	public BigInteger getAge() {
		return age;
	}
	public void setAge(BigInteger age) {
		this.age = age;
	}
	
	
}

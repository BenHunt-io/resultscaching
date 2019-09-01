package com.benhunt.controller;

import java.math.BigInteger;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MultivaluedMap;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.benhunt.repository.PersonRepository;

/**
 *
 * @author airhacks.com
 */
@Path("eclipselink")
public class PersonController {

    @Inject
    @ConfigProperty(name = "message")
    String message;
    
    private static final Logger LOGGER = Logger.getLogger(PersonController.class.getName());
    
    @Inject
    PersonRepository personRepository;
    
    @GET
    public String ping() {
    	return "Ben says hi from the eclipse link caching demo";
    }
    
    @GET
    @Path("getPeopleByLastName")
    public List<String> getPeopleByName(@QueryParam("lastName") String lastName) {
    	return personRepository.getPersonsByLastName(lastName);
    }
    
    @GET
    @Path("getPeopleByLastNameCached")
    public List<String> getPeopleByNameCached(@QueryParam("lastName") String lastName) {
    	return personRepository.getPersonsByLastNameCached(lastName);
    }
    
    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Transactional
    public void insertPerson(MultivaluedMap<String, String> formParam) {
    	LOGGER.log(Level.INFO, "Inserting Person" + formParam.getFirst("firstName") + " " + formParam.getFirst("lastName") + " " + formParam.getFirst("age"));
    	personRepository.insertPerson(formParam.getFirst("firstName"), formParam.getFirst("lastName"), new BigInteger(formParam.getFirst("age")));
    }

}

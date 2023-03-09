package com.elciocestari.resources;

import com.elciocestari.entities.User;
import com.elciocestari.services.UserService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/users")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class UserResource {

    @Inject
    UserService service;
    @GET
    public List<User> users() {
        return service.findAll();
    }
}
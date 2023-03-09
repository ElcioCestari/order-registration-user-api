package com.elciocestari.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/users")
public class UserResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String users() {
        return "users";
    }
}
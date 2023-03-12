package com.elciocestari.resources;

import com.elciocestari.dtos.UserRequestDTO;
import com.elciocestari.dtos.UserResponseDTO;
import com.elciocestari.services.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/users")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class UserResource {

    @Inject
    UserService service;
    @GET
    public List<UserResponseDTO> users() {
        return service.findAll();
    }

    @POST
    public Response save(UserRequestDTO dto) {
        return Response.status(201).entity(service.save(dto)).build();
    }
}
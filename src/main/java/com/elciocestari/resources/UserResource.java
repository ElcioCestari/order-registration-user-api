package com.elciocestari.resources;

import com.elciocestari.dtos.UserRequestDTO;
import com.elciocestari.dtos.UserResponseDTO;
import com.elciocestari.services.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.*;
import static javax.ws.rs.core.Response.status;

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
        return status(CREATED).entity(service.save(dto)).build();
    }

    @PUT
    @Path("/{username}")
    public Response save(@PathParam("username") String username, UserRequestDTO dto) {
        return status(OK).entity(service.update(username, dto)).build();
    }

    @DELETE
    @Path("/{username}")
    public Response delete(@PathParam("username") String username) {
        return status(NO_CONTENT).entity(service.delete(username)).build();
    }
}
package org.acme.resource;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.validation.Valid;

import org.acme.dto.UserDTO;
import org.acme.entity.User;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @GET
    public List<UserDTO> getAll() {
        return User.<User>listAll().stream().map(u -> {
            UserDTO dto = new UserDTO();
            dto.setId(u.id);
            dto.setUsername(u.username);
            dto.setRole(u.role);
            return dto;
        }).collect(Collectors.toList());
    }

    @POST
    @Transactional
    public Response create(@Valid UserDTO dto) {
        if (dto.getUsername() == null || dto.getPassword() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Username dan password tidak boleh null")
                    .build();
        }

        User user = new User();
        user.username = dto.getUsername();
        user.password = dto.getPassword(); // TODO: 🔒 hash dulu jika perlu
        user.role = dto.getRole() != null ? dto.getRole() : "user";
        user.persist();

        UserDTO result = new UserDTO();
        result.setId(user.id);
        result.setUsername(user.username);
        result.setRole(user.role);

        return Response.created(URI.create("/users/" + user.id)).entity(result).build();
    }
}

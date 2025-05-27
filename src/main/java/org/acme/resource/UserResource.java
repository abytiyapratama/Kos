package org.acme.resource;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
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
            dto.id = u.id;
            dto.username = u.username;
            dto.role = u.role;
            return dto;
        }).collect(Collectors.toList());
    }

    @POST
    @Transactional
    public Response create(@Valid UserDTO dto) {
        if (dto.username == null || dto.password == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Username dan password tidak boleh null")
                    .build();
        }

        User user = new User();
        user.username = dto.username;
        user.password = dto.password; // 🔒 TODO: Hash password sebelum simpan ke DB
        user.role = dto.role != null ? dto.role : "user";
        user.persist();

        UserDTO result = new UserDTO();
        result.id = user.id;
        result.username = user.username;
        result.role = user.role;

        return Response.created(URI.create("/users/" + user.id)).entity(result).build();
    }
}

package org.acme.resource;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.dto.UserDTO;
import org.acme.entity.User;

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
    public void create(UserDTO dto) {
        User user = new User();
        user.username = dto.username;
        user.password = dto.password; // hash password jika perlu
        user.role = dto.role != null ? dto.role : "user";
        user.persist();
    }
}

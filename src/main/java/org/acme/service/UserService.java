package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.dto.UserDTO;
import org.acme.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserService {

    public List<UserDTO> getAll() {
        return User.<User>listAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public void create(UserDTO dto) {
        if (dto == null || dto.username == null || dto.password == null) {
            throw new IllegalArgumentException("Username dan password wajib diisi");
        }

        boolean exists = User.find("username", dto.username).firstResult() != null;
        if (exists) {
            throw new IllegalArgumentException("Username sudah digunakan");
        }

        User user = new User();
        user.username = dto.username;
        user.password = dto.password; // jika perlu hashing bisa ditambahkan di sini
        user.role = dto.role != null ? dto.role : "user";
        user.persist();
    }

    private UserDTO toDTO(User u) {
        UserDTO dto = new UserDTO();
        dto.id = u.id;
        dto.username = u.username;
        dto.role = u.role;
        return dto;
    }
}

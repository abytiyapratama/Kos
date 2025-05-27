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
        if (dto == null || dto.getUsername() == null || dto.getPassword() == null) {
            throw new IllegalArgumentException("Username dan password wajib diisi");
        }

        boolean exists = User.find("username", dto.getUsername()).firstResult() != null;
        if (exists) {
            throw new IllegalArgumentException("Username sudah digunakan");
        }

        User user = new User();
        user.username = dto.getUsername();
        user.password = dto.getPassword(); // 🔒 hash jika perlu
        user.role = dto.getRole() != null ? dto.getRole() : "user";
        user.persist();
    }

    private UserDTO toDTO(User u) {
        UserDTO dto = new UserDTO();
        dto.setId(u.id);
        dto.setUsername(u.username);
        dto.setRole(u.role);
        return dto;
    }
}

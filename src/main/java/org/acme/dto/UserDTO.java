package org.acme.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDTO {
    public Long id;

    @NotBlank(message = "Username tidak boleh kosong")
    @Size(min = 4, max = 20, message = "Username harus 4-20 karakter")
    public String username;

    @NotBlank(message = "Password tidak boleh kosong")
    @Size(min = 6, message = "Password minimal 6 karakter")
    public String password;

    // Optional: hanya digunakan jika peran mau ditentukan manual (default "user")
    public String role;
}

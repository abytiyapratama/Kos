package org.acme.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

public class PengeluaranDTO {
    public Long id;

    @NotNull(message = "User ID tidak boleh kosong")
    public Long userId;

    @NotNull(message = "Kategori ID tidak boleh kosong")
    public Long kategoriId;

    @NotNull(message = "Jumlah tidak boleh kosong")
    @Positive(message = "Jumlah harus lebih dari 0")
    public Double jumlah;

    @NotNull(message = "Tanggal tidak boleh kosong")
    public LocalDate tanggal;

    public String catatan;
}

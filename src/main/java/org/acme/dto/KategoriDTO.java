package org.acme.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class KategoriDTO {
    public Long id;

    @NotBlank(message = "Nama kategori tidak boleh kosong")
    public String nama;

    @NotBlank(message = "Tipe kategori harus diisi (pemasukan/pengeluaran)")
    public String tipe; // hanya boleh "pemasukan" atau "pengeluaran"
}

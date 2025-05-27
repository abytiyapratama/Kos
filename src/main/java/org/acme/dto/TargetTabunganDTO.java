package org.acme.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class TargetTabunganDTO {
    public Long id;

    @NotNull(message = "User ID tidak boleh kosong")
    public Long userId;

    @NotNull(message = "Nama target tidak boleh kosong")
    public String nama;

    @NotNull(message = "Target nominal tidak boleh kosong")
    @Positive(message = "Target nominal harus lebih dari 0")
    public Double targetNominal;

    public Boolean tercapai;
}

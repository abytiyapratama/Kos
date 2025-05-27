package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.dto.KategoriDTO;
import org.acme.entity.Kategori;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class KategoriService {

    public List<KategoriDTO> getAll() {
        return Kategori.<Kategori>listAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void create(KategoriDTO dto) {
        if (dto == null || dto.nama == null || dto.tipe == null) {
            throw new IllegalArgumentException("Data kategori tidak valid.");
        }

        Kategori k = new Kategori();
        k.nama = dto.nama;
        k.tipe = dto.tipe;
        k.persist();
    }

    private KategoriDTO toDTO(Kategori k) {
        KategoriDTO dto = new KategoriDTO();
        dto.id = k.id;
        dto.nama = k.nama;
        dto.tipe = k.tipe;
        return dto;
    }
}

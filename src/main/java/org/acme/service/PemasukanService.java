package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.dto.PemasukanDTO;
import org.acme.entity.Kategori;
import org.acme.entity.Pemasukan;
import org.acme.entity.User;

import java.util.List;

@ApplicationScoped
public class PemasukanService {

    public List<Pemasukan> getAll() {
        return Pemasukan.<Pemasukan>listAll();
    }

    @Transactional
    public void create(PemasukanDTO dto) {
        if (dto == null || dto.userId == null || dto.kategoriId == null || dto.jumlah == null || dto.tanggal == null) {
            throw new IllegalArgumentException("Data pemasukan tidak lengkap");
        }

        User user = User.findById(dto.userId);
        Kategori kategori = Kategori.findById(dto.kategoriId);

        if (user == null || kategori == null) {
            throw new IllegalArgumentException("User atau Kategori tidak ditemukan");
        }

        Pemasukan p = new Pemasukan();
        p.jumlah = dto.jumlah;
        p.tanggal = dto.tanggal;
        p.catatan = dto.catatan;
        p.user = user;
        p.kategori = kategori;

        p.persist();
    }
}

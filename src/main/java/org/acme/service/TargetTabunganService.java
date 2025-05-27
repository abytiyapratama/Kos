package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.dto.TargetTabunganDTO;
import org.acme.entity.TargetTabungan;
import org.acme.entity.User;

import java.util.List;

@ApplicationScoped
public class TargetTabunganService {

    public List<TargetTabungan> getAll() {
        return TargetTabungan.<TargetTabungan>listAll(); // fix: cast generic type
    }

    @Transactional
    public void create(TargetTabunganDTO dto) {
        if (dto == null || dto.userId == null || dto.nama == null || dto.targetNominal == null) {
            throw new IllegalArgumentException("Data target tabungan tidak lengkap");
        }

        User user = User.findById(dto.userId);
        if (user == null) {
            throw new IllegalArgumentException("User tidak ditemukan");
        }

        TargetTabungan t = new TargetTabungan();
        t.nama = dto.nama;
        t.targetNominal = dto.targetNominal;
        t.tercapai = dto.tercapai != null ? dto.tercapai : false;
        t.user = user;

        t.persist();
    }
}

package org.acme.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pemasukan")
public class Pemasukan extends PanacheEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

    @ManyToOne
    @JoinColumn(name = "kategori_id")
    public Kategori kategori;

    @Column(nullable = false)
    public Double jumlah;

    @Column(nullable = false)
    public LocalDate tanggal;

    public String catatan;
}

package org.acme.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "kategori")
public class Kategori extends PanacheEntity {

    @Column(nullable = false)
    public String nama;

    @Column(nullable = false)
    public String tipe;

    @OneToMany(mappedBy = "kategori")
    public List<Pemasukan> pemasukan;

    @OneToMany(mappedBy = "kategori")
    public List<Pengeluaran> pengeluaran;
}

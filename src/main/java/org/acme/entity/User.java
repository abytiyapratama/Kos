package org.acme.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends PanacheEntity {

    @Column(nullable = false, unique = true)
    public String username;

    @Column(nullable = false)
    public String password;

    @Column(nullable = false)
    public String role = "user";

    @OneToMany(mappedBy = "user")
    public List<Pemasukan> pemasukan;

    @OneToMany(mappedBy = "user")
    public List<Pengeluaran> pengeluaran;

    @OneToMany(mappedBy = "user")
    public List<TargetTabungan> targetTabungan;
}

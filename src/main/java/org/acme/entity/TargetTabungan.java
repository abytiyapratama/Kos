package org.acme.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "target_tabungan")
public class TargetTabungan extends PanacheEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

    @Column(nullable = false)
    public String nama;

    @Column(nullable = false)
    public Double targetNominal;

    @Column(nullable = false)
    public Boolean tercapai = false;
}

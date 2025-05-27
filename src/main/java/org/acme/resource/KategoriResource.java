package org.acme.resource;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.dto.KategoriDTO;
import org.acme.entity.Kategori;

import java.util.List;
import java.util.stream.Collectors;

@Path("/kategori")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class KategoriResource {

    @GET
    public List<KategoriDTO> getAll() {
        List<Kategori> list = Kategori.listAll(); // ✅ pastikan bertipe List<Kategori>
        return list.stream().map(k -> {
            KategoriDTO dto = new KategoriDTO();
            dto.id = k.id;
            dto.nama = k.nama;
            dto.tipe = k.tipe;
            return dto;
        }).collect(Collectors.toList());
    }

    @POST
    @Transactional
    public void create(KategoriDTO dto) {
        Kategori k = new Kategori();
        k.nama = dto.nama;
        k.tipe = dto.tipe;
        k.persist();
    }
}

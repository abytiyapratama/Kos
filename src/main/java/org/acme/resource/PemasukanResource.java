package org.acme.resource;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.dto.PemasukanDTO;
import org.acme.entity.*;

import java.util.List;
import java.util.stream.Collectors;

@Path("/pemasukan")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PemasukanResource {

    @GET
    public List<PemasukanDTO> getAll() {
        return Pemasukan.<Pemasukan>listAll().stream().map(p -> {
            PemasukanDTO dto = new PemasukanDTO();
            dto.id = p.id;
            dto.jumlah = p.jumlah;
            dto.tanggal = p.tanggal;
            dto.catatan = p.catatan;
            dto.userId = p.user != null ? p.user.id : null;
            dto.kategoriId = p.kategori != null ? p.kategori.id : null;
            return dto;
        }).collect(Collectors.toList());
    }

    @POST
    @Transactional
    public void create(PemasukanDTO dto) {
        Pemasukan p = new Pemasukan();
        p.jumlah = dto.jumlah;
        p.tanggal = dto.tanggal;
        p.catatan = dto.catatan;

        p.user = User.findById(dto.userId);
        p.kategori = Kategori.findById(dto.kategoriId);

        p.persist();
    }
}

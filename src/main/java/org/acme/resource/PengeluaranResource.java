package org.acme.resource;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.dto.PengeluaranDTO;
import org.acme.entity.*;

import java.util.List;
import java.util.stream.Collectors;

@Path("/pengeluaran")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PengeluaranResource {

    @GET
    public List<PengeluaranDTO> getAll() {
        return Pengeluaran.<Pengeluaran>listAll().stream().map(p -> {
            PengeluaranDTO dto = new PengeluaranDTO();
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
    public void create(PengeluaranDTO dto) {
        Pengeluaran p = new Pengeluaran();
        p.jumlah = dto.jumlah;
        p.tanggal = dto.tanggal;
        p.catatan = dto.catatan;

        p.user = User.findById(dto.userId);
        p.kategori = Kategori.findById(dto.kategoriId);

        p.persist();
    }
}

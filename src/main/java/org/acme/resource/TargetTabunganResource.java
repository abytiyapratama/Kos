package org.acme.resource;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.dto.TargetTabunganDTO;
import org.acme.entity.*;

import java.util.List;
import java.util.stream.Collectors;

@Path("/target")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TargetTabunganResource {

    @GET
    public List<TargetTabunganDTO> getAll() {
        return TargetTabungan.<TargetTabungan>listAll().stream().map(t -> {
            TargetTabunganDTO dto = new TargetTabunganDTO();
            dto.id = t.id;
            dto.nama = t.nama;
            dto.targetNominal = t.targetNominal;
            dto.tercapai = t.tercapai;
            dto.userId = t.user != null ? t.user.id : null;
            return dto;
        }).collect(Collectors.toList());
    }

    @POST
    @Transactional
    public void create(TargetTabunganDTO dto) {
        TargetTabungan t = new TargetTabungan();
        t.nama = dto.nama;
        t.targetNominal = dto.targetNominal;
        t.tercapai = dto.tercapai != null ? dto.tercapai : false;
        t.user = User.findById(dto.userId);
        t.persist();
    }
}

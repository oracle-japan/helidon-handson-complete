
package com.example.handson.helidon;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("prefecture")
public class PrefectureResource {

    @PersistenceContext(unitName = "test")
    private EntityManager entityManager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Prefecture> getPrefectures() {
        return entityManager.createNamedQuery("getPrefectures", Prefecture.class).getResultList();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Prefecture getPrefectureById(@PathParam("id") String id) {
        TypedQuery<Prefecture> query = entityManager.createNamedQuery("getPrefectureById", Prefecture.class);
        Prefecture prefecture = new Prefecture();
        try {
            prefecture = query.setParameter("id", Integer.valueOf(id)).getSingleResult();
        } catch (NoResultException ne) {
            throw new NotFoundException("Unable to find prefecture with ID " + id);
        }
        return prefecture;
    }

    @GET
    @Path("name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Prefecture getPrefectureByName(@PathParam("name") String name) {
        TypedQuery<Prefecture> query = entityManager.createNamedQuery("getPrefectureByName", Prefecture.class);
        List<Prefecture> list = query.setParameter("name", name).getResultList();
        if (list.isEmpty()) {
            throw new NotFoundException("Unable to find prefecture with name " + name);
        }
        return list.get(0);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional(Transactional.TxType.REQUIRED)
    public void deletePrefecture(@PathParam("id") String id) {
        Prefecture prefecture = getPrefectureById(id);
        entityManager.remove(prefecture);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional(Transactional.TxType.REQUIRED)
    public void createPrefecture(Prefecture prefecture) {
        try {
            PrefectureArea prefectureArea = entityManager
                    .createNamedQuery("getPrefectureAreaByArea", PrefectureArea.class)
                    .setParameter("area", prefecture.getArea()).getSingleResult();
            prefecture.setPrefectureArea(prefectureArea);
            entityManager.persist(prefecture);
        } catch (Exception e) {
            throw new BadRequestException("Unable to create prefecture with ID " + prefecture.getId());
        }
    }
}

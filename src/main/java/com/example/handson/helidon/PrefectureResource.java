
package com.example.handson.helidon;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
            PrefectureArea prefectureArea = entityManager.createNamedQuery("getPrefectureAreaByArea", PrefectureArea.class)
                    .setParameter("area", prefecture.getArea()).getSingleResult();
            prefecture.setPrefectureArea(prefectureArea);
            entityManager.persist(prefecture);
        } catch (Exception e) {
            throw new BadRequestException("Unable to create prefecture with ID " + prefecture.getId());
        }
    }
}

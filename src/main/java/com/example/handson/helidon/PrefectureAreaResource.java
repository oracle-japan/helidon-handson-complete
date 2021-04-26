
package com.example.handson.helidon;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("area")
public class PrefectureAreaResource {

    @PersistenceContext(unitName = "test")
    private EntityManager entityManager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PrefectureArea> getPrefectureArea() {
        return entityManager.createNamedQuery("getPrefectureAreas", PrefectureArea.class).getResultList();
    }
}
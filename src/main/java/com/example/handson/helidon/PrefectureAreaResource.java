
package com.example.handson.helidon;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

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
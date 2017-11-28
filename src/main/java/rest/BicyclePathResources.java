package rest;

import domain.BicyclePath;
import domain.Movie;
import domain.services.BicyclePathService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/bicyclepath")
public class BicyclePathResources {
    private BicyclePathService db = BicyclePathService.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<BicyclePath> getAll() {
        return db.getAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Add(BicyclePath bicyclePath) {
        db.add(bicyclePath);
        return Response.ok(bicyclePath.getId()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") int id) {
        BicyclePath result = db.get(id);
        if (result == null)
            return Response.status(404).build();
        return Response.ok(result).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, BicyclePath a) {
        BicyclePath result = db.get(id);
        if (result == null)
            return Response.status(404).build();
        a.setId(id);
        db.update(a);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        BicyclePath result = db.get(id);
        if (result == null)
            return Response.status(404).build();
        db.delete(result);
        return Response.ok().build();
    }
}

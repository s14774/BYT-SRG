package rest;

import domain.BicyclePath;
import domain.Movie;
import domain.Passing;
import domain.services.BicyclePathService;
import domain.services.PassingService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path("/bicyclepath")
public class BicyclePathResources {
    private BicyclePathService bpdb = BicyclePathService.getInstance();
    private PassingService pdb = PassingService.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<BicyclePath> getAll() {
        return bpdb.getAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Add(BicyclePath bicyclePath) {
        bpdb.add(bicyclePath);
        return Response.ok(bicyclePath.getId()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") int id) {
        BicyclePath result = bpdb.get(id);
        if (result == null)
            return Response.status(404).build();
        return Response.ok(result).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, BicyclePath bicyclePath) {
        BicyclePath result = bpdb.get(id);
        if (result == null)
            return Response.status(404).build();
        bicyclePath.setId(id);
        bpdb.update(bicyclePath);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        BicyclePath bicyclePath = bpdb.get(id);
        if (bicyclePath == null)
            return Response.status(404).build();
        bpdb.delete(bicyclePath);
        return Response.ok().build();
    }
    //dodaj przejazd
    @POST
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Add(@PathParam("id") int id, Passing passing) {
        BicyclePath bicyclePath = bpdb.get(id);
        if (bicyclePath == null)
            return Response.status(404).build();
        passing.setBicyclePath(bicyclePath);
        passing.setDate(new Date());
        pdb.add(passing);
        return Response.ok().build();
    }
    //usun przejazd
    //import

}

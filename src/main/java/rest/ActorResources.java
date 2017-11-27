package rest;

import domain.Actor;
import domain.Movie;
import domain.services.ActorService;
import domain.services.MovieService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/actor")
public class ActorResources {
    private ActorService db = ActorService.getInstance();
    private MovieService movieDB = MovieService.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Actor> getAll() {
        return db.getAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Add(Actor Actor) {
        db.add(Actor);
        return Response.ok(Actor.getId()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") int id) {
        Actor result = db.get(id);
        if (result == null)
            return Response.status(404).build();
        return Response.ok(result).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, Actor a) {
        Actor result = db.get(id);
        if (result == null)
            return Response.status(404).build();
        a.setId(id);
        db.update(a);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        Actor result = db.get(id);
        if (result == null)
            return Response.status(404).build();
        db.delete(result);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}/movie")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movie> getMovies(@PathParam("id") int id) {
        Actor result = db.get(id);
        if (result == null)
            return null;
        List<Movie> output = new ArrayList<>();
        for (Movie m : movieDB.getAll()) {
            if (m.getActor(id) != null)
                output.add(m);
        }
        return output;
    }

    @POST
    @Path("/{id}/movie")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMovie(@PathParam("id") int id, Movie movie) {
        Actor result = db.get(id);
        if (result == null)
            return Response.status(404).build();
        Movie mId = movieDB.get(movie.getId());
        Movie mName = movieDB.get(movie.getName());
        if (mId == null) {
            if (mName == null)
                movieDB.add(movie);
            else
                movie = mName;
        } else {
            if (mName == null)
                movie = mId;
            else if (mId == mName)
                movie = mId;
            else
                return Response.status(404).build();
        }
        //result.getMovies().add(movie);
        movie.getActors().add(result);
        return Response.ok().build();
    }
}

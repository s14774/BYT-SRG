package rest;

import domain.Actor;
import domain.Comment;
import domain.Movie;
import domain.Rating;
import domain.services.MovieService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/movie")
public class MovieResources {
    private MovieService db = MovieService.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movie> getAll() {
        return db.getAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Add(Movie movie) {
        db.add(movie);
        return Response.ok(movie.getId()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") int id) {
        Movie result = db.get(id);
        if (result == null)
            return Response.status(404).build();
        return Response.ok(result).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, Movie m) {
        Movie result = db.get(id);
        if (result == null)
            return Response.status(404).build();
        m.setId(id);
        db.update(m);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        Movie result = db.get(id);
        if (result == null)
            return Response.status(404).build();
        db.delete(result);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}/comments")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comment> getComments(@PathParam("id") int id) {
        Movie result = db.get(id);
        if (result == null)
            return null;
        if (result.getComments() == null)
            result.setComments(new ArrayList<>());
        return result.getComments();
    }

    @POST
    @Path("/{id}/comments")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addComment(@PathParam("id") int id, Comment comment) {
        Movie result = db.get(id);
        if (result == null)
            return Response.status(404).build();
        if (result.getComments() == null)
            result.setComments(new ArrayList<>());
        result.addComment(comment);
        db.update(result);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}/comments/{commentId}")
    public Response deleteComment(@PathParam("id") int id, @PathParam("commentId") int commentId) {
        Movie result = db.get(id);
        if (result == null)
            return Response.status(404).build();
        if (result.getComments() == null)
            result.setComments(new ArrayList<>());

        result.removeComment(commentId);
        db.update(result);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}/rating")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer getRating(@PathParam("id") int id) {
        Movie result = db.get(id);
        if (result == null)
            return null;
        return result.getRating();
    }

    @POST
    @Path("/{id}/rating")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addRating(@PathParam("id") int id, Rating rating) {
        Movie result = db.get(id);
        if (result == null)
            return Response.status(404).build();
        if (rating == null)
            return Response.status(404).build();
        result.addRating(rating);
        db.update(result);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}/actor")
    @Produces(MediaType.APPLICATION_JSON)
    public Actor getActor(@PathParam("id") int id) {
        Movie result = db.get(id);
        if (result == null)
            return null;
        return result.getActor(id);
    }
}

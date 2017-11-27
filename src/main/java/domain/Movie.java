package domain;

import java.util.ArrayList;
import java.util.List;

public class Movie {

    private int id;
    private int commentId = 1;
    private int ratingId = 1;
    private String name;
    private List<Comment> comments;
    private List<Rating> ratings;
    private List<Actor> actors;

    public void addActor(Actor actor) {
        if (this.getActors() == null)
            this.setActors(new ArrayList<>());
        this.getActors().add(actor);
    }

    public List<Actor> getActors() {
        if (this.actors == null)
            this.setActors(new ArrayList<>());
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public Actor getActor(int id) {
        if (this.getActors() == null)
            this.setActors(new ArrayList<>());

        for (Actor a : this.getActors()) {
            if (a.getId() == id)
                return a;
        }
        return null;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public void addRating(Rating rating) {
        if (this.ratings == null) this.ratings = new ArrayList<>();
        rating.setId(++ratingId);
        this.ratings.add(rating);
    }

    public Integer getRating() {
        if (this.ratings == null) this.ratings = new ArrayList<>();
        int suma = 0, i = 0;
        for (Rating r : ratings) {
            suma += r.getRating();
            i++;
        }
        if (i == 0)
            return null;
        return suma / i;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(Comment comment) {
        if (this.comments == null) this.comments = new ArrayList<>();
        comment.setId(++commentId);
        this.comments.add(comment);
    }

    public void removeComment(Integer id) {
        Comment c = null;
        for (Comment comment : comments)
            if (comment.getId() == id)
                c = comment;
        comments.remove(c);
    }
}

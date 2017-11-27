package domain.services;

import domain.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieService {
    private static MovieService instance = null;
    private static List<Movie> db = new ArrayList<>();
    private static int currentID = 1;

    protected MovieService() {
    }

    public static MovieService getInstance() {
        if (instance == null) {
            instance = new MovieService();
        }
        return instance;
    }

    public Movie get(int id) {
        for (Movie m : db)
            if (m.getId() == id)
                return m;
        return null;
    }

    public Movie get(String name) {
        for (Movie m : db)
            if (m.getName().equals(name))
                return m;
        return null;
    }

    public void add(Movie m) {
        m.setId(++currentID);
        db.add(m);
    }

    public void update(Movie movie) {
        for (Movie m : db)
            if (m.getId() == movie.getId()) {
                m.setName(movie.getName());
            }
    }

    public void delete(Movie m) {
        db.remove(m);
    }

    public List<Movie> getAll() {
        return db;
    }
}

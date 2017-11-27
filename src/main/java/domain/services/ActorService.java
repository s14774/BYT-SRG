package domain.services;

import domain.Actor;

import java.util.ArrayList;
import java.util.List;

public class ActorService {
    private static ActorService instance = null;
    private static List<Actor> db = new ArrayList<>();
    private static int currentID = 1;

    protected ActorService() {
    }

    public static ActorService getInstance() {
        if (instance == null) {
            instance = new ActorService();
        }
        return instance;
    }

    public Actor get(int id) {
        for (Actor a : db)
            if (a.getId() == id)
                return a;
        return null;
    }

    public void add(Actor a) {
        a.setId(++currentID);
        db.add(a);
    }

    public void update(Actor actor) {
        for (Actor a : db)
            if (a.getId() == actor.getId()) {
                a.setName(actor.getName());
                a.setSurname(actor.getSurname());
            }
    }

    public void delete(Actor a) {
        db.remove(a);
    }

    public List<Actor> getAll() {
        return db;
    }
}

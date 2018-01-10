package domain.services;

import domain.Passing;
import domain.Passing;

import java.util.ArrayList;
import java.util.List;

public class PassingService {
    private static PassingService instance = null;
    private static List<Passing> db = new ArrayList<>();
    private static int currentID = 1;

    protected PassingService() {
    }

    public static PassingService getInstance() {
        if (instance == null) {
            instance = new PassingService();
        }
        return instance;
    }

    public Passing get(int id) {
        for (Passing p : db)
            if (p.getId() == id)
                return p;
        return null;
    }

    public void add(Passing p) {
        p.setId(++currentID);
        db.add(p);
    }

    public void delete(Passing m) {
        db.remove(m);
    }

    public List<Passing> getAll() {
        return db;
    }
}

package domain.services;

import domain.BicyclePath;
import domain.Passing;

import java.util.ArrayList;
import java.util.Date;
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

    public List<Passing> getWhereBicyclePath(BicyclePath bicyclePath) {
        if(bicyclePath == null) return new ArrayList<>();
        List<Passing> result = new ArrayList<>();
        for (Passing p : db)
            if (p.getBicyclePath().equals(bicyclePath))
                result.add(p);
        return result;
    }

    public List<Passing> getFromLast15minutes(BicyclePath bicyclePath) {
        if(bicyclePath == null) return new ArrayList<>();
        List<Passing> result = new ArrayList<>();
        for (Passing p : db)
            if (p.getBicyclePath().equals(bicyclePath) && new Date(System.currentTimeMillis() - (15 * 60 * 1000)).before(p.getDate()))
                result.add(p);
        return result;
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

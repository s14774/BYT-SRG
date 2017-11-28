package domain.services;

import domain.BicyclePath;

import java.util.ArrayList;
import java.util.List;

public class BicyclePathService {
    private static BicyclePathService instance = null;
    private static List<BicyclePath> db = new ArrayList<>();
    private static int currentID = 1;

    protected BicyclePathService() {
    }

    public static BicyclePathService getInstance() {
        if (instance == null) {
            instance = new BicyclePathService();
        }
        return instance;
    }

    public BicyclePath get(int id) {
        for (BicyclePath bp : db)
            if (bp.getId() == id)
                return bp;
        return null;
    }

    public void add(BicyclePath bp) {
        bp.setId(++currentID);
        db.add(bp);
    }

    public void update(BicyclePath bicyclePath) {
        for (BicyclePath bp : db)
            if (bp.getId() == bicyclePath.getId()) {
                bp.setName(bicyclePath.getName());
            }
    }

    public void delete(BicyclePath bp) {
        db.remove(bp);
    }

    public List<BicyclePath> getAll() {
        return db;
    }
}

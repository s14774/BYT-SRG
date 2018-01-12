package domain.services;

import domain.BicyclePath;
import domain.Passing;
import domain.helpers.HourQuantity;

import java.util.ArrayList;
import java.util.Calendar;
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

    public Integer getAmountFromLast15minutes(BicyclePath bicyclePath) {
        if(bicyclePath == null) return null;
        Integer result = 0;
        for (Passing p : db)
            if (
                p.getBicyclePath().equals(bicyclePath) &&
                new Date(System.currentTimeMillis() - (15 * 60 * 1000)).before(p.getDate()) &&
                new Date().after(p.getDate())
            )
                result++;
        return result;
    }

    public List<HourQuantity> getAmountFromLastWeek(BicyclePath bicyclePath) {
        if(bicyclePath == null) return new ArrayList<>();
        List<HourQuantity> hql = new ArrayList<>();

        List<Passing> passingsBP = new ArrayList<>();
        for (Passing p : db)
            if (
                p.getBicyclePath().equals(bicyclePath) &&
                new Date(System.currentTimeMillis() - (7 * 24 * 60 * 60 * 1000)).before(p.getDate()) &&
                new Date().after(p.getDate())
            )
                passingsBP.add(p);

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);

        for (int i=7*24; i>=0; i--){
            HourQuantity hq = new HourQuantity((cal.get(Calendar.HOUR_OF_DAY)-i+24*7)%24);
            for (Passing p : passingsBP)

                if (
                    new Date(cal.getTimeInMillis() - (i * 60 * 60 * 1000)).before(p.getDate()) &&
                    new Date(cal.getTimeInMillis() - ((i-1) * 60 * 60 * 1000)).after(p.getDate())
                )
                    hq.addQuantity(1);
            hql.add(hq);
        }
        return hql;
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

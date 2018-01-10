package domain;

import java.util.Date;

public class Passing {

    private int id;
    private BicyclePath bicyclePath;
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BicyclePath getBicyclePath() {
        return bicyclePath;
    }

    public void setBicyclePath(BicyclePath bicyclePath) {
        this.bicyclePath = bicyclePath;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

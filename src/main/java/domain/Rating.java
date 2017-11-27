package domain;

public class Rating {
    private int id;
    private int rating;

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = RatingChecker(rating);
    }

    private int RatingChecker(int rating) {
        if (rating < 0)
            return 0;
        if (rating > 10)
            return 10;
        return rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

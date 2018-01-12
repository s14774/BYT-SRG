package domain.helpers;

public class HourQuantity {

    private int hour;
    private int quantity = 0;

    public HourQuantity() {
    }

    public HourQuantity(int hour) {
        this.hour = hour;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }
}

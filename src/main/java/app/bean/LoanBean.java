package app.bean;

import java.io.Serializable;

public class LoanBean implements Serializable {

    private double price;
    private double rate;
    private int count;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double percent) {
        this.rate = percent;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getInstallment(){
        if (count<1) throw new IllegalArgumentException();
        return (price*(rate/1200))/(1-(1/Math.pow(1+(rate/1200), count)));
    }
}

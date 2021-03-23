package Store;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

public abstract class Product implements Rateable<Product> {
    public static final BigDecimal DISCOUNT_RATE = BigDecimal.valueOf(0.1);
    private int id;
    private String name;
    private BigDecimal price;
    private Rating rating;

//    Product() {
//        this(0, "n/a", BigDecimal.ZERO);
//    }
    //Get ride off public keyword to make them accessible just by classes in same package.
    //Interfaces allows u to inherit code from more than 1 class.
    Product(int id, String name, BigDecimal price, Rating rating) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

//    public abstract Product applyRating(Rating newRating);

    public BigDecimal getDiscount() {
        return price.multiply(DISCOUNT_RATE).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public Rating getRating() {
        return rating;
    }

    public LocalDate getBestBefore() {
        return LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    //    public abstract void serve();

    @Override
    public String toString() {
        return "Id: "+this.id+" Name: "+this.name+" Price: "+this.price+" Rating: "+this.rating.getStarts();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return  61 * hash + this.id;
    }

    @Override
    public boolean equals(Object obj) {//Called by putIfAbsence method of HashMap
        if(this == obj) {
            return true;
        }
        if(obj instanceof Product) {
            final  Product other = (Product) obj;
            return this.id == other.id && Objects.equals(this.name, other.name);
        }
        return false;
    }
}

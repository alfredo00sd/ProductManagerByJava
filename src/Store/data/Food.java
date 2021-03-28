package Store.data;

import java.math.BigDecimal;
import java.time.LocalDate;

public final class Food extends Product {

    private LocalDate bestBefore;

    Food(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore) {
        super(id, name, price, rating);
        this.bestBefore = bestBefore;
    }

    public LocalDate getBestBefore() {
        return bestBefore;
    }


    @Override
    public String toString() {
        return super.toString() + ", " + bestBefore;
    }

    @Override
    public Product applyRating(Rating rating) {
        return null;
    }

    //    @Override
//    public void serve() {
//        System.out.println("Serving a food...");
//    }
}

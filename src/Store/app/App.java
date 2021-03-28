package Store.app;

import Store.data.Product;
import Store.data.ProductManager;

import java.math.BigDecimal;
import java.util.Comparator;
import static Store.data.Rating.*;

public class App {

    public static void main(String[] args) {

        ProductManager pm = new ProductManager("en-US");

        pm.createProduct(1, "Tea", BigDecimal.valueOf(1.99), NOT_RATED);
        pm.reviewProduct(1, THREE_STARTS, "Good enough");
        pm.parseReview("s, 4, Nice one");
        pm.printProductReport(1);

        //pm.changeLocal("ru-RU");

        //Interchange p1 or p2 for descending or ascending order
        Comparator<Product> ratingSorter = (p1, p2) -> p2.getRating().ordinal() - p1.getRating().ordinal();
        Comparator<Product> priceSorter = (p1, p2) -> p2.getPrice().compareTo(p1.getPrice());

        //pm.printProducts(ratingSorter);
        pm.printProducts(p -> p.getPrice().floatValue() < 2, ratingSorter.thenComparing(priceSorter));//Combine sorters
        pm.printProducts(p -> p.getPrice().floatValue() > 2, ratingSorter.thenComparing(priceSorter).reversed());//Combine sorters reverse
    }
}

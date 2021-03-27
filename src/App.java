import Store.*;

import java.math.BigDecimal;
import java.util.Comparator;
import static Store.Rating.*;

public class App {

    public static void main(String[] args) {

        ProductManager pm = new ProductManager("en-US");

        pm.createProduct(1, "Tea", BigDecimal.valueOf(1.99), NOT_RATED);
        pm.reviewProduct(1, NOT_RATED, "IDK");
        pm.reviewProduct(1, THREE_STARTS, "Good enough");
        pm.reviewProduct(1, FIVE_STARTS, "I love it!");
        pm.reviewProduct(1, FOUR_STARTS, "Made me happy");
//        pm.printProductReport(1   );

        pm.changeLocal("ru-RU");

        pm.createProduct(2, "Blue", BigDecimal.valueOf(90), NOT_RATED);
        pm.reviewProduct(2, NOT_RATED, "IDK");
        pm.reviewProduct(2, THREE_STARTS, "Good enough");
        pm.reviewProduct(2, FIVE_STARTS, "I love it!");
        pm.reviewProduct(2, FOUR_STARTS, "Made me happy");
//        pm.printProductReport(2);

        pm.changeLocal("zh-CN");

        pm.createProduct(3, "Corn-flakes", BigDecimal.valueOf(12.34), NOT_RATED);
        pm.reviewProduct(3, NOT_RATED, "IDK");
        pm.reviewProduct(3, THREE_STARTS, "Good enough");
        pm.reviewProduct(3, FIVE_STARTS, "I love it!");
        pm.reviewProduct(3, FOUR_STARTS, "Made me happy");
//        pm.printProductReport(3);

        pm.changeLocal("es-ES");

        pm.createProduct(4, "Jabon", BigDecimal.valueOf(14.99), NOT_RATED);
        pm.reviewProduct(4, NOT_RATED, "IDK");
        pm.reviewProduct(4, THREE_STARTS, "Good enough");
        pm.reviewProduct(4, FIVE_STARTS, "I love it!");
        pm.reviewProduct(4, FOUR_STARTS, "Made me happy");
//        pm.printProductReport(4);

        //Interchange p1 or p2 for descending or ascending order
        Comparator<Product> ratingSorter = (p1, p2) -> p2.getRating().ordinal() - p1.getRating().ordinal();
        Comparator<Product> priceSorter = (p1, p2) -> p2.getPrice().compareTo(p1.getPrice());

//        pm.printProducts(ratingSorter);
        pm.printProducts(p -> p.getPrice().floatValue() < 2, ratingSorter.thenComparing(priceSorter));//Combine sorters
        pm.printProducts(p -> p.getPrice().floatValue() > 2, ratingSorter.thenComparing(priceSorter).reversed());//Combine sorters reverse
    }

    public static void order(Product product) {
        //product.serve();
    }
}

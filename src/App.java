import Store.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;

import static Store.Rating.*;

public class App {

    public static void main(String[] args) {

        ProductManager pm = new ProductManager(Locale.US);

        pm.createProduct(1, "Tea", BigDecimal.valueOf(1.99), NOT_RATED);
        pm.reviewProduct(1, NOT_RATED, "IDK");
        pm.reviewProduct(1, THREE_STARTS, "Good enough");
        pm.reviewProduct(1, FIVE_STARTS, "I love it!");
        pm.reviewProduct(1, FOUR_STARTS, "Made me happy");
        pm.printProductReport(1);

        pm.createProduct(2, "Blue", BigDecimal.valueOf(90), NOT_RATED);
        pm.reviewProduct(2, NOT_RATED, "IDK");
        pm.reviewProduct(2, THREE_STARTS, "Good enough");
        pm.reviewProduct(2, FIVE_STARTS, "I love it!");
        pm.reviewProduct(2, FOUR_STARTS, "Made me happy");
        pm.printProductReport(2);

        pm.createProduct(3, "Corn-flakes", BigDecimal.valueOf(12.34), NOT_RATED);
        pm.reviewProduct(3, NOT_RATED, "IDK");
        pm.reviewProduct(3, THREE_STARTS, "Good enough");
        pm.reviewProduct(3, FIVE_STARTS, "I love it!");
        pm.reviewProduct(3, FOUR_STARTS, "Made me happy");
        pm.printProductReport(3);

        pm.createProduct(4, "Jabon", BigDecimal.valueOf(14.99), NOT_RATED);
        pm.reviewProduct(4, NOT_RATED, "IDK");
        pm.reviewProduct(4, THREE_STARTS, "Good enough");
        pm.reviewProduct(4, FIVE_STARTS, "I love it!");
        pm.reviewProduct(4, FOUR_STARTS, "Made me happy");
        pm.printProductReport(4);
    }

    public static void order(Product product) {
        //product.serve();
    }
}

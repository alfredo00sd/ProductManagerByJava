import Store.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;

import static Store.Rating.*;

public class App {

    public static void main(String[] args) {

        ProductManager pm = new ProductManager(Locale.US);

        //Product p2 = pm.createProduct(3, "Cake", BigDecimal.valueOf(3.99), FIVE_STARTS, LocalDate.now().plusDays(3));
        Product p2 = pm.createProduct(2, "Tea", BigDecimal.valueOf(1.99), NOT_RATED);
        pm.printProductReport(p2);
        pm.reviewProduct(p2, NOT_RATED, "IDK");
        pm.reviewProduct(p2, THREE_STARTS, "Good enough");
        pm.reviewProduct(p2, FIVE_STARTS, "I love it!");
        pm.reviewProduct(p2, FOUR_STARTS, "Made me happy");
        pm.printProductReport(p2);
    }

    public static void order(Product product) {
        //product.serve();
    }
}

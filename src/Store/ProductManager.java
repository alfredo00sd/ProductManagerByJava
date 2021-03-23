package Store;

import java.text.MessageFormat;
import java.text.NumberFormat;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

public class ProductManager {

    private Locale locale;
    private ResourceBundle resources;
    private DateTimeFormatter dateFormatter;
    private NumberFormat moneyFormatter;

//    private Review[] reviews = new Review[5];
//    private Product product;
    private Map<Product, List<Review>> products = new HashMap<>();

    public ProductManager(Locale locale) {
        this.locale = locale;
        resources = ResourceBundle.getBundle("Store.resources", locale);
        dateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(locale);
        moneyFormatter = NumberFormat.getCurrencyInstance(locale);
    }

    public Product createProduct(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore) {
        Product product = new Food(id, name, price, rating, bestBefore);
        products.putIfAbsent(product, new ArrayList<>());

        return  product;

    }

    public Product createProduct(int id, String name, BigDecimal price, Rating rating) {
        Product product = new Drink(id, name, price, rating);
        products.putIfAbsent(product, new ArrayList<>());

        return product;
    }
    //Should return product because we are adding a review to the product...
    public Product reviewProduct(Product product, Rating rating, String comments) {

        int sum = 0;
        //Get the list of reviews of a product
        List<Review> reviews = products.get(product);
        //Recreate the product getting rid of the old one and put the new one...
        products.remove(product, reviews);

        reviews.add(new Review(rating, comments));

        for(Review review : reviews) {//Calc total sum of ratings

            sum += review.getRating().ordinal();
        }

        //Calculate rating based on the Average of reviews.
        product = product.applyRating(Rateable.convert(Math.round((float) sum / reviews.size())));

        products.put(product, reviews);

        return product;
    }
    public Product reviewProduct(int id, Rating rating, String comments) {
        return reviewProduct(findProduct(id), rating, comments);
    }

    public Product findProduct(int id) {
        Product result = null;
        for (Product product : products.keySet()) {
            if(product.getId() == id) {
                result = product;
                break;
            }
        }
        return result;
    }

    public void printProductReport(int id) {
        printProductReport(findProduct(id));
    }

    public void printProductReport(Product product) {
        List<Review> reviews = products.get(product);
        StringBuilder txt = new StringBuilder();
        txt.append(MessageFormat.format(resources.getString("product"),
                                        product.getName(),
                                        moneyFormatter.format(product.getPrice()),
                                        product.getRating().getStarts(),
                                        dateFormatter.format(product.getBestBefore())));
        txt.append("\n");
        Collections.sort(reviews);
        for(Review review : reviews) {

            txt.append(MessageFormat.format(resources.getString("review"),
                    review.getRating().getStarts(),
                    review.getComments()));
            txt.append("\n");
        }

        if(reviews.isEmpty()) {
            txt.append(resources.getString("no.reviews"));
            txt.append("\n");
        }
        System.out.println(txt);
    }
}

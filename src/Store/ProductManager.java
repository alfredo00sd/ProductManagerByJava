package Store;

import java.text.MessageFormat;
import java.text.NumberFormat;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

public class ProductManager {

    private Locale locale;
    private ResourceBundle resources;
    private DateTimeFormatter dateFormatter;
    private NumberFormat moneyFormatter;

    private Review[] reviews = new Review[5];
    private Product product;

    public ProductManager(Locale locale) {
        this.locale = locale;
        resources = ResourceBundle.getBundle("Store.resources", locale);
        dateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(locale);
        moneyFormatter = NumberFormat.getCurrencyInstance(locale);
    }

    public Product createProduct(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore) {
        product = new Food(id, name, price, rating, bestBefore);

        return  product;
    }

    public Product createProduct(int id, String name, BigDecimal price, Rating rating) {
        product = new Drink(id, name, price, rating);

        return product;
    }
    //Should return product because we are adding a review to the product...
    public Product reviewProduct(Product product, Rating rating, String comments) {
//        reviews = new Review(rating, comments);
        if(reviews[reviews.length - 1] != null) {//Now u can do more reviews for same product
            reviews = Arrays.copyOf(reviews, reviews.length + 5);
        }
        int counter = 0;
        int sum = 0;
        boolean reviewed = false;
        while (counter < reviews.length && !reviewed) {
            if(reviews[counter] == null) {
                reviews[counter] = new Review(rating, comments);
                reviewed = true;
            }
            sum += reviews[counter].getRating().ordinal();//
            counter++;
        }
        //Calculate rating based on the Average of reviews.
        this.product = product.applyRating(Rateable.convert(Math.round((float) sum / counter)));// rating
        return product;
    }

    public void printProductReport() {
        StringBuilder txt = new StringBuilder();
        txt.append(MessageFormat.format(resources.getString("product"),
                                        product.getName(),
                                        moneyFormatter.format(product.getPrice()),
                                        product.getRating().getStarts(),
                                        dateFormatter.format(product.getBestBefore())));
        txt.append("\n");
        for(Review reviews : reviews) {

            if (reviews == null)
                break;

            txt.append(MessageFormat.format(resources.getString("review"),
                    reviews.getRating().getStarts(),
                    reviews.getComments()));
            txt.append("\n");
        }
        if(reviews[0] == null) {
            txt.append(resources.getString("no.reviews"));
            txt.append("\n");
        }
        System.out.println(txt);
    }
}

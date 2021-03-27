package Store;

import java.text.MessageFormat;
import java.text.NumberFormat;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProductManager {

    private final Map<Product, List<Review>> products = new HashMap<>();
    private ResourceFormatter formatter;
    private static final Map<String, ResourceFormatter> formatters =  new HashMap<String, ResourceFormatter>(){
        {
            put("en-GB", new ResourceFormatter(Locale.UK));
            put("en-US", new ResourceFormatter(Locale.US));
            put("es-ES", new ResourceFormatter(new Locale("es","ES")));
            put("fr-FR", new ResourceFormatter(Locale.FRANCE));
            put("ru-RU", new ResourceFormatter(new Locale("ru", "RU")));
            put("zh-CN", new ResourceFormatter(Locale.CHINA));
        }
    };

    public ProductManager(Locale locale) {
        this(locale.toLanguageTag());
    }

    public ProductManager(String languageTag) {
        changeLocal(languageTag);
    }

    public void changeLocal(String languageTag) {
        formatter = formatters.getOrDefault(languageTag, formatters.get("en-US"));
    }

    public static Set<String> getSupportedLocals() {
        return formatters.keySet();
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

        //Get the list of reviews of a product
        List<Review> reviews = products.get(product);
        //Recreate the product getting rid of the old one and put the new one...
        products.remove(product, reviews);
        reviews.add(new Review(rating, comments));

        product = product.applyRating(Rateable.convert((int) Math.round(
                            reviews.stream().mapToInt(r -> r.getRating().ordinal()).average().orElse(0))));

        products.put(product, reviews);

        return product;
    }

    public Product reviewProduct(int id, Rating rating, String comments) {
        return reviewProduct(findProduct(id), rating, comments);
    }

    public Product findProduct(int id) {

        return products.keySet().stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    //Print more than one report in a particular order.
    public void printProducts(Predicate<Product> filter, Comparator<Product> sorter) {

        StringBuilder txt = new StringBuilder();

        products.keySet().stream().filter(filter).sorted(sorter).forEach(p -> txt.append(formatter.formatProduct(p)).append("\n"));
        System.out.println(txt);
    }

    public void printProductReport(int id) {
        printProductReport(findProduct(id));
    }

    public void printProductReport(Product product) {
        List<Review> reviews = products.get(product);
        StringBuilder txt = new StringBuilder();
        txt.append(formatter.formatProduct(product));

        txt.append("\n");
        Collections.sort(reviews);

        if(reviews.isEmpty()) {
            txt.append(formatter.getText("no.reviews")).append("\n");
        }else {
            //Collector is better for parallel processing
            txt.append(reviews.stream().map(r -> formatter.formatReview(r) + "\n").collect(Collectors.joining()));
        }
        System.out.println(txt);
    }

    //Move declarations related to formatting here
    public static class ResourceFormatter {
        private final ResourceBundle resources;
        private final DateTimeFormatter dateFormatter;
        private final NumberFormat moneyFormatter;

        private ResourceFormatter(Locale locale) {

            resources = ResourceBundle.getBundle("Store.resources", locale);
            dateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(locale);
            moneyFormatter = NumberFormat.getCurrencyInstance(locale);
        }

        private String formatProduct(Product product) {
            return MessageFormat.format(resources.getString("product"),
                                        product.getName(),
                                        moneyFormatter.format(product.getPrice()), product.getRating().getStarts(),
                                        dateFormatter.format(product.getBestBefore()));
        }

        private String formatReview(Review review) {

            return MessageFormat.format(resources.getString("review"),
                                        review.getRating().getStarts(), review.getComments());
        }

        private String getText(String key) {

            return resources.getString(key);
        }
    }
}
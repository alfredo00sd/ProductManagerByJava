package Store.data;

public class ProductManagerException extends Exception {

    public ProductManagerException() {

    }

    public ProductManagerException(String msg) {
        super(msg);
    }

    public ProductManagerException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

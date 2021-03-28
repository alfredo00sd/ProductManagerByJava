package Store.data;

@FunctionalInterface//to not accidentally add another abstract method
public interface Rateable<T> { //<T> make it generic, T could be whatever but T conventionally is Type.

    Rating DEFAULT_RATING = Rating.NOT_RATED;

    T applyRating(Rating rating);

    default T applyRating(int starts) {
        return applyRating(convert(starts));
    }

    static Rating convert(int starts) {
        return (starts >= 0 && starts <= 5) ? Rating.values()[starts] : DEFAULT_RATING;
    }

    default Rating getRating() {
        return DEFAULT_RATING;
    }
}
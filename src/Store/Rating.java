package Store;

public enum Rating {
    NOT_RATED("\u2606\u2606\u2606\u2606\u2606"),
    ONE_STARTS("\u2605\u2606\u2606\u2606\u2606"),
    TWO_STARTS("\u2605\u2605\u2606\u2606\u2606"),
    THREE_STARTS("\u2605\u2605\u2605\u2606\u2606"),
    FOUR_STARTS("\u2605\u2605\u2605\u2605\u2606"),
    FIVE_STARTS("\u2605\u2605\u2605\u2605\u2605");

    private final String starts;

    Rating(String s) {
        this.starts = s;
    }

    public String getStarts() {
        return starts;
    }
}

package Store.data;

public class Review implements Comparable<Review> {
    private Rating rating;
    private String comments;

    public Review(Rating rating, String comments) {
        this.rating = rating;
        this.comments = comments;
    }

    public Rating getRating() {
        return rating;
    }

    public String getComments() {
        return comments;
    }

    @Override
    public String toString() {
        return "Rating: "+rating+", Comment: "+comments;
    }

    @Override
    public int compareTo(Review other) {//To sort reviews to some order
        return other.rating.ordinal() - this.rating.ordinal();
        //0 is same
        //- negative or + positive value
    }
}

public class BasicReview extends Review {
    public BasicReview(User user, double rating, String comment, Movie movie) {
        super(user, rating, comment, movie);
    }

    @Override
    public double getWeightedRating() {
        return rating;
    }
} 
public class VerifiedReview extends Review {
    public VerifiedReview(User user, double rating, String comment, Movie movie) {
        super(user, rating, comment, movie);
    }

    @Override
    public double getWeightedRating() {
        return rating * 1.2;
    }
}

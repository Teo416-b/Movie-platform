public class Review implements Printable {
    protected User user;
    protected double rating;
    protected String comment;
    protected Movie movie;

    // Constructor
    public Review(User user, double rating, String comment, Movie movie) {
        this.user = user;
        this.rating = rating;
        this.comment = comment;
        this.movie = movie;
    }

    public double getRating() {
        return rating;
    }

    public String getComment(){
        return comment;
    }
    public User getUser(){
        return user;
    }
    public String getName(){
        return user.getUsername();
    }
    public Movie getMovie(){
        return movie;
    }
    public String getMovieTitle(){
        return movie.getTitle();
    }

    public double getWeightedRating() {
        return rating *1.2;
    }

    @Override
    public void printDetails() {
        System.out.println(user.getUsername() + " rated " + movie.getTitle() + " with " + rating + "/10");
        if (comment != null && !comment.isEmpty()) {
            System.out.println("Comment: " + comment);
        }
    }
}

// Movie.java
import java.util.*;

public class Movie implements Printable {
    private String title;
    private int year;
    private List<String> genres;
    private String director;
    private List<Review> reviews;
    private List<Movie> relatedMovies;

    public Movie(String title, int year, List<String> genres, String director) {
        this.title = title;
        this.year = year;
        this.genres = new ArrayList<>(genres);
        this.director = director;
        this.reviews = new ArrayList<>();
        this.relatedMovies = new ArrayList<>();
    }

    public void addReview(Review r) {
        reviews.add(r);
    }

    public void addRelatedMovie(Movie m) {
        relatedMovies.add(m);
    }

    public double getAverageRating() {
        if (reviews.isEmpty()) return 0;
        double total = 0;
        for (Review r : reviews){
            total += r.getWeightedRating();
        }
        return (double) total / reviews.size();
    }

	public double getAboveSeven(){
		int total = 0;
		for (Review r : reviews){
			if(r.getRating() > 7)
				total++;
		}
		return (double) total / reviews.size();
	}

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public List<String> getGenres() {
        return genres;
    }

    public String getDirector() {
        return director;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public List<Movie> getRelatedMovies() {
        return relatedMovies;
    }

    public void printDetails() {
        System.out.println("Title: " + title);
        System.out.println("Year: " + year);
        System.out.println("Genres: " + genres);
        System.out.println("Director: " + director);
        if(getAverageRating()>10)
            System.out.println("Average Rating: 10");
        else
            System.out.format("Average rating of %.2f", +getAverageRating());

    }
} 


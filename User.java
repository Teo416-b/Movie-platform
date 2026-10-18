import java.util.*;

public class User implements Printable {
    protected String username;
    protected String critic;
    protected List<Review> reviews;

    public User(String username, String critic) {
        this.username = username;
        this.critic = critic;
        this.reviews = new ArrayList<>();
    }

    public void addReview(Review r) {
        reviews.add(r);
    }

    public String getUsername() {
        return username;
    }

    public List<Review> getReviews() {
        return reviews;
    }
	
    public String getCritic(){
        return critic;
    }

	public double getAverageUserR(){
		double sum = 0;
		
		for(Review r : reviews)
			sum += r.getRating();
		
		return (double) sum / reviews.size();
	}

    @Override
    public void printDetails() {
        if(critic.equalsIgnoreCase("yes")){
            System.out.println("[CRITIC] User: " + username);
            System.out.println("Reviews submitted: " + reviews.size());
        }
        else if(critic.equalsIgnoreCase("no")){
            System.out.println("User: " + username);
            System.out.println("Reviews submitted: " + reviews.size());
        }
    }
}


// Main.java
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<User> users = new ArrayList<>();
        List<Movie> movies = new ArrayList<>();
        List<Review> reviews = new ArrayList<>();
        List<User> watched = new ArrayList<>();
        

        // Initial users
        User alice = new User("alice", "no");
        User bob = new User("bob", "yes");
        User charlie = new User("charlie", "no");
        users.add(alice);
        users.add(bob);
        users.add(charlie);

        // Initial movies
        Movie inception = new Movie("Inception", 2010, List.of("Sci-Fi", "Action"), "Christopher Nolan");
        Movie matrix = new Movie("The Matrix", 1999, List.of("Sci-Fi", "Action"), "Wachowski Sisters");
        Movie parasite = new Movie("Parasite", 2019, List.of("Drama", "Thriller"), "Bong Joon-ho");
		Movie godfather = new Movie("The Godfather", 1972, List.of("Drama", "Crime"), " Francis Ford Coppola");
        movies.add(inception);
        movies.add(matrix);
        movies.add(parasite);

        // Related movies
        inception.addRelatedMovie(matrix);
        matrix.addRelatedMovie(inception);
        parasite.addRelatedMovie(inception);

        // Initial reviews
        Review r1 = new Review(alice, 8, "Mind-blowing!", inception);
        Review r2 = new Review(bob, 8, "Great visuals and plot", inception);
        Review r3 = new Review(charlie, 7, "Solid movie", matrix);
        Review r4 = new Review(bob, 8, "Masterpiece", parasite);
        reviews.add(r1);
        reviews.add(r2);
        reviews.add(r3);
        reviews.add(r4);
        
        inception.addReview(r1);
        inception.addReview(r2);
        matrix.addReview(r3);
        parasite.addReview(r4);

        alice.addReview(r1);
        bob.addReview(r2);
        charlie.addReview(r3);
        bob.addReview(r4);

        //Demo
        System.out.println("DEMO\n\n");
        System.out.println("Demo of options 1 and 4");
        System.out.println("Add movie\n");
        System.out.println("Title: The matrix");
        System.out.println("Year: 1999");
        System.out.println("Genres (comma-separated): Sci-fi, Action");
        System.out.println("Director: Wachowski Sisters");
        System.out.println("Movie added.\n");
        System.out.println("\nShow all movies\n");
        inception.printDetails();
        System.out.println();
        matrix.printDetails();
        System.out.println("\n\n");
        System.out.println("Demo of options 2 and 5");
        System.out.println("Add user-critic user\n");
        System.out.println("Add user\n");
        System.out.println("Username: bob");
        System.out.println("Is Critic? (yes/no): yes(or no)");
        System.out.println("User added.\n");
        System.out.println("\nShow all users\n");
        alice.printDetails();
        System.out.println();
        bob.printDetails();
        System.out.print("Show the average rating each user sumbits?(yes/no) ");
        System.out.print(" yes");
        alice.getAverageUserR();
        System.out.println();
        bob.getAverageUserR();
        System.out.println("\n\n");
        System.out.println("Demo of option 3");
        System.out.println("Add review\n");
        System.out.println("Movie title: Inception");
        System.out.println("Username: bob");
        System.out.println("Comment (optional): Great visuals and plot");
        System.out.println("Review added.\n");
        System.out.println();
        r2.printDetails();System.out.println("User added.\n");
        System.out.println("\nDemo of option 7");
        System.out.println("Search movie\n");
        System.out.println("Movie title the matrix\n");
        matrix.printDetails();
        System.out.println("\nShow reviews");
        System.out.println("yes\n");
        r3.printDetails();
        //end demo
        int checkr = 0;
        int checkm = 0; 
        int checku = 0;
        int flag = 0;
        while (true) {

        if(checkm == 0){ //reader for movies
            String csvFileM = "movies.csv";
            String line;
            String csvSplitByM = ",";
                try (BufferedReader br = new BufferedReader(new FileReader(csvFileM))) {
                br.readLine(); 

                while ((line = br.readLine()) != null) {
                    String[] data = line.split(csvSplitByM, -1);
                    if (data.length == 4) {
                        String title = data[0];
                        int year = Integer.parseInt(data[1]);
                        List<String> genres = List.of(data[2].split(";"));
                        String director = data[3];

                        boolean found = false;
                        for(Movie f : movies){
                            if(f.getTitle().equals(title)){
                                found = true;
                                break;
                            }
                        }
                        if(found != true){
                            Movie m = new Movie(title, year, genres, director);
                            movies.add(m);
                        }   
                    }
                }
            }
            catch (IOException e) {
                System.out.println("error ");
            }
            checkm = 1;
        }

        if(checku == 0){ //reader for users
            String csvFileU = "user.csv";
            String line;
            String csvSplitByU = ",";
            try (BufferedReader br = new BufferedReader(new FileReader(csvFileU))) {
                br.readLine(); 

                while ((line = br.readLine()) != null){
                    String[] data = line.split(csvSplitByU, -1);
                    if (data.length == 2){
                        String username = data[0];
                        String critic = data[1];

                        boolean found = false;
                        for(User a : users){
                            if(a.getUsername().equals(username))
                                found = true;
                                break;
                        }
                        if(found != true){
                        User u = new User(username, critic);
                        users.add(u);   
                        }
                                                    
                    } 
                }
            }
            catch (IOException e) {
                System.out.println("error ");
            }
            checkm = 1;
        }

        if(checkr == 0){ //reader for reviews
            String csvFileR = "reviews.csv";
            String line;
            String csvSplitByR = ",";
            try (BufferedReader br = new BufferedReader(new FileReader(csvFileR))) {
            br.readLine(); 

                while ((line = br.readLine()) != null) {
                    String[] data = line.split(csvSplitByR);

                    if (data.length == 4) {
                        String username = data[0];
                        double rating = Double.parseDouble(data[1]);
                        String comment = data[2];
                        String movieTitle = data[3];

                        
                        User user = null;
                        for (User u : users) {
                            if (u.getUsername().equals(username)) {
                                user = u;
                                break;
                            }
                        }

                        
                        Movie movie = null;
                        for (Movie m : movies) {
                            if (m.getTitle().equals(movieTitle)) {
                                movie = m;
                                break;
                            }
                        }

                        
                        if (user != null && movie != null) {
                            boolean found = false;
                            for(Review p : reviews){
                                String temp = p.getName();
                                if(temp.equals(username) && p.getComment().equals(comment)){
                                    found = true;
                                    break;
                                }
                            }
                            if(found != true){
                                Review r = new Review(user, rating, comment, movie);
                                reviews.add(r);
                                user.addReview(r);  
                                movie.addReview(r);
                            }  
                        }
                    }
                }
            }
            catch (IOException e) {
                System.out.println("error ");
            }
            checkr = 1;
        }

            System.out.println("\n=== MOVIE PLATFORM MENU ===");
            System.out.println("1. Add Movie");
            System.out.println("2. Add User");
            System.out.println("3. Add Review");
            System.out.println("4. Show All Users");
            System.out.println("5. Show All Movies");
            System.out.println("6. Show top 5 movies per genre");
            System.out.println("7. Show movies with high acceptance ");
            System.out.println("8. Search Movie by title");
            System.out.println("9. Search movies by genre");
            System.out.println("10. Search Related Movies");
			System.out.println("11. Search Movie by Minimum rating ");
            System.out.println("0. Exit");
            if(flag == 0){
                System.out.println();
                System.out.println("Scroll up for demo.\n");
                flag++;
            }
            System.out.print("Choose: ");
            int choice = Integer.parseInt(scanner.nextLine());
            System.out.println();

            if (choice == 0) break;

            switch (choice) {
                case 1: // Add movie
                    System.out.print("Title: ");
                    String title = scanner.nextLine();
                    boolean exists = movies.stream().anyMatch(m -> m.getTitle().equalsIgnoreCase(title));
                    if(exists == true){
                        System.out.println("This movie already exists in the list");
                        System.out.print("\nPress Enter to continue ");
                        String con = scanner.nextLine();
                        if(true)
                            break;
                    }
                    System.out.print("Year: ");
                    int year = Integer.parseInt(scanner.nextLine());

                    System.out.print("Genres (;-separated): ");
                    List<String> genres = List.of(scanner.nextLine().split(";"));

                    System.out.print("Director: ");
                    String director = scanner.nextLine();

                    String genresString = String.join(";", genres);
                    String newLine = title + "," + year + "," + genresString + "," + director;

                    String CsvFile = "movies.csv";
                    try(BufferedWriter bw = new BufferedWriter(new FileWriter(CsvFile, true))){

                        bw.write(newLine);
                        bw.newLine(); 
                    
                    }//try
                    catch (IOException e) {
                        System.out.println	("Error writing file ...");
                    }

                    movies.add(new Movie(title, year, genres, director));
                    System.out.println("Movie added.\n");
                    System.out.print("\nPress Enter to continue ");
                    String con = scanner.nextLine();
                    if(true)
                        break;

                case 2: // Add user
                    
                    for(;;){
                        System.out.print("Username: ");
                        String username = scanner.nextLine();
                        boolean taken = users.stream().anyMatch(u -> u.getUsername().equalsIgnoreCase(username));
                        if(username.isEmpty()){
                            System.out.println("You can't have a blank username. Try again\n");
                            continue;
                        }
                        else if(taken == true){
                            System.out.println("This username is taken. Please choose a different one\n");
                            continue;
                        }
                        for(;;){
                            System.out.print("Is Critic? (yes/no): ");
                            String type = scanner.nextLine();
                            if (type.equalsIgnoreCase("yes") || type.equalsIgnoreCase("no")) {

                                newLine = username + "," +type;
                                CsvFile = "user.csv";

                                try(BufferedWriter bw = new BufferedWriter(new FileWriter(CsvFile, true))){

                                    bw.write(newLine);
                                    bw.newLine(); 
                                
                                }//try
                                catch (IOException e) {
                                    System.out.println	("Error writing file ...");
                                }
                                users.add(new User(username, type));
                                break;
                            }
                            else
                                System.out.println("Type only yes or on. Try again\n");
                            }
                        System.out.println("User added.\n");
                        break;
                    }
                    System.out.print("\nPress Enter to continue ");
                    con = scanner.nextLine();
                    if(true)
                        break;
                case 3: // Add review
                    System.out.print("Movie title: ");
                    String movieTitle = scanner.nextLine();
                    Movie movie = movies.stream().filter(m -> m.getTitle().equalsIgnoreCase(movieTitle)).findFirst().orElse(null);
                    if (movie == null) {
                        System.out.println("Movie not found.\n");
                        System.out.print("\nPress Enter to continue ");
                        con = scanner.nextLine();
                        if(true)
                            break;
                    }
                    System.out.print("Username: ");
                    String usern = scanner.nextLine();
                    User usr = users.stream().filter(u -> u.getUsername().equalsIgnoreCase(usern)).findFirst().orElse(null);
                    if (usr == null) {
                        System.out.println("User not found.\n");
                        System.out.print("\nPress Enter to continue ");
                        con = scanner.nextLine();
                        if(true)
                            break;
                    }
                    if(usr.getCritic().equals("yes")){
                        System.out.print("Rate 1-10: ");
                        double rate = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Comment (optional): ");
                        String comment = scanner.nextLine();

                        newLine = usern +"," +rate + "," +comment +"," +movieTitle;

                        CsvFile = "reviews.csv";
                        try(BufferedWriter bw = new BufferedWriter(new FileWriter(CsvFile, true))){

                            bw.write(newLine);
                            bw.newLine(); 
                    
                        }//try
                        catch (IOException e) {
                        System.out.println	("Error writing file ...");
                    }

                        Review review = new VerifiedReview(usr, rate, comment, movie);
                        movie.addReview(review);
                        usr.addReview(review);
                        reviews.add(review);
                        System.out.println("Review added.\n");
                    }
                    else{
                        System.out.print("Rate 1-10: ");
                        double rate = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Comment (optional): ");
                        String comment = scanner.nextLine();
                        Review review = new BasicReview(usr, rate, comment, movie);
                        movie.addReview(review);
                        usr.addReview(review);
                        reviews.add(review);
                        System.out.println("Review added.\n");
                    }
                    System.out.print("\nPress Enter to continue ");
                    con = scanner.nextLine();
                    if(true)
                        break;

                case 4: // Show all users
                    for (User u : users) {
                        u.printDetails();
                        System.out.println();
                    }
					System.out.print("Show the average rating each user sumbits?(yes/no) ");
					for(;;){
						String answer = scanner.nextLine();
						if (answer.equalsIgnoreCase("yes")){

							for (User u : users){
								System.out.println();
								System.out.print("Average rating of " +u.getUsername() +":");
								System.out.println(u.getAverageUserR());
							}
							break;
						}
						else if(answer.equalsIgnoreCase("no"))
							break;
						
						else{
							System.out.println("Type only yes or no. Try again\n");
							continue;
						}
					}
                    System.out.print("\nPress Enter to continue ");
                    con = scanner.nextLine();
                    if(true)
                        break;

                case 5: // Show all movies
                    for (Movie m : movies) {
                        m.printDetails();
                        System.out.println();
                    }
                    System.out.print("\nPress Enter to continue ");
                    con = scanner.nextLine();
                    if(true)
                        break;

                case 6: // Show top 5 movies per genre
					flag = 0;
                    System.out.print("Type genre: ");
					String select = scanner.nextLine();
                    System.out.println("\nTop movies in genre: " + select +"\n");
					movies.sort((m1, m2) -> Double.compare(m2.getAverageRating(), m1.getAverageRating()));
					for (Movie f : movies){
						for (String g : f.getGenres()) {
                            if (g.toLowerCase().equals(select)) {
                                f.printDetails();
                                System.out.println();
							}
						}
						flag++;
						if(flag == 6)
							break;
					}
                    System.out.print("\nPress Enter to continue ");
                    con = scanner.nextLine();
                    if(true)
                        break;



                case 7: //show movies with high acceptance (80% of the ratings are above >7)
					
					System.out.print("Minimum reviews per movie: ");
                    int minReviews = Integer.parseInt(scanner.nextLine());
					System.out.println("\nTop movies: ");
                    for (Movie m: movies){

						if((m.getReviews().size() >= minReviews) && (0.8 <=  m.getAboveSeven())){
                            m.printDetails();
                            System.out.println();
                        }
                    }
                    System.out.print("\nPress Enter to continue ");
                    con = scanner.nextLine();
                    if(true)
                        break;

                case 8: // Search movie by title
                    boolean found = false;
                    System.out.print("Search: ");
                    String search = scanner.nextLine().toLowerCase();
                    for (Movie m : movies){
                        if (m.getTitle().toLowerCase().contains(search)){
                            m.printDetails();
                            System.out.println();
                            found = true;
                            if (!m.getReviews().isEmpty()){
                                for(;;){
                                    System.out.print("Show Reviews?(yes/no) ");
                                    String ans = scanner.nextLine();
                                    if (ans.equalsIgnoreCase("yes")){

                                        System.out.println("\nReviews: ");
                                        for (Review r : m.getReviews()){
                                            r.printDetails();
                                            System.out.println();
                                        }
                                        break;
                                    }
                                    else if(ans.equalsIgnoreCase("no"))
                                        break;
                                    else{
                                        System.out.println("Type only yes or no. Try again\n");
                                        continue;
                                    }
                                }
                            }
                                
                        }
                    }
                    
                    if(found != true)
                        System.out.println("The list doesn't contian this movie\n");

                    System.out.print("\nPress Enter to continue ");
                    con = scanner.nextLine();
                    if(true)
                        break;

                case 9: // Search movies by genre
                    System.out.print("Genre: ");
                    String genreSearch = scanner.nextLine().toLowerCase();
                    found = false;
                    for (Movie m : movies) {
                        for (String g : m.getGenres()) {
                            if (g.toLowerCase().equals(genreSearch)) {
                                m.printDetails();
                                System.out.println();
                                found = true;
                            }
                        }
                    }
                    if (!found) {
                        System.out.println("No movies found with genre: " + genreSearch);
                    }
                    System.out.print("\nPress Enter to continue ");
                    con = scanner.nextLine();
                    if(true)
                        break;

                case 10: // Search related movies

                    System.out.print("Movie title: ");
                    String baseTitle = scanner.nextLine();
                    Movie baseMovie = movies.stream().filter(m -> m.getTitle().equalsIgnoreCase(baseTitle)).findFirst().orElse(null);
                    if (baseMovie == null) {
                        System.out.println("Movie not found.");
                        System.out.print("\nPress Enter to continue ");
                        con = scanner.nextLine();
                        if(true)
                            break;
                    }
                    System.out.println("\nRelated movies: \n");
                    found = false;
                    for (Movie rel : movies){
                        if(baseTitle.equalsIgnoreCase(rel.getTitle())){
                            continue;
                        }
                        else{
                            for (String genre : rel.getGenres()) {
                                for (String baseGenre : baseMovie.getGenres()) {
                                    if (baseGenre.equalsIgnoreCase(genre)) {
                                        rel.printDetails();
                                        System.out.println();
                                        baseMovie.addRelatedMovie(rel);
                                        found = true;
                                    }
                                    if(found != false)
                                        break;
                                }
                            }
                        }
                    }
                    System.out.print("\nPress Enter to continue ");
                    con = scanner.nextLine();
                    if(true)
                        break;

                 case 11: //Search Movie by Minimum rating
                    System.out.print("Minimum average rating: ");
                    double minRating = Double.parseDouble(scanner.nextLine());
                    movies.stream()
                        .filter(m -> m.getAverageRating() >= minRating)
                        .sorted(Comparator.comparingDouble(Movie::getAverageRating).reversed())
                        .forEach(m -> {
                            m.printDetails();
                            System.out.println();
                        });
                    System.out.print("\nPress Enter to continue ");
                    con = scanner.nextLine();
                    if(true)
                        break;			

                default:
                    System.out.println("Invalid option.");
                    System.out.print("\nPress Enter to continue ");
                    con = scanner.nextLine();
                    if(true)
                        break;
            }
        }
        scanner.close();
        System.out.println("Exiting program.");
    }
}

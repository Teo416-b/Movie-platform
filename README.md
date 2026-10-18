Java Movie Platform

Description:
A console-based Object-Oriented Java application that simulates a movie platform. The program allows users to explore a database of movies, submit reviews, search for films based on various criteria, create/see users and see recommendations. It also features data persistence using CSV files.

System Architecture:
1. Movie: Stores the title, release year, genres, director, user reviews, and related movies. Key methods include getAverageRating, addReview, addRelatedMovie, and printDetails.
2. Review: An abstract class storing the user, rating, comment, and the associated movie. Extended by BasicReview and VerifiedReview. Contains the abstract method getWeightedRating.
3. User: Stores the username, a list of submitted reviews, and a flag indicating if they are a critic.
4. Printable: An interface implemented by all main classes (Movie, Review, User) to standardize the printDetails method.
5. Data Files: movies.csv, user.csv, and reviews.csv are used to store and load all the platform's data permanently.
6. Main: Handles the initialization of objects, manages CSV data, and controls the interactive CLI menu.

How to Run and Menu Options:
Compile all the .java files and run the Main class. You interact with the program by typing the number corresponding to your desired action (0-11):

1. Add movie: Insert a new movie by providing Title, Year, Genres, and Director.
2. Add user: Register a new user and specify if they are a regular user or a Critic (Critics ratings have a higher weight).
3. Add review: Rate a movie (1-10) and optionally leave a comment.
4. Show all users: Displays registered users and their total submitted reviews.
5. Show all movies: Displays all movies, their details, and average ratings.
6. Show top 5 Movies of a Genre: Discover the top 5 highest-rated movies for a specific genre.
7. Show Movies with high acceptance: View movies that are highly rated by the audience (e.g. 80 percent of reviews are above 7), based on a minimum number of reviews.
8. Search Movie by title: Find a specific movie and optionally read all its reviews.
9. Search Movie by Genre: Filter and view movies belonging to a specific genre.
10. Search related movies: View a list of movies related to a specific title (based on common genres).
11. Search Movie by Minimum rating: Filter movies that have an average rating above a certain threshold.
0. Exit: Terminates the program and saves data.
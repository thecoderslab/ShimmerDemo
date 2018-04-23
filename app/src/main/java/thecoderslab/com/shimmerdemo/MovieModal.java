package thecoderslab.com.shimmerdemo;


public class MovieModal {


    String posterPath;
    String title;
    double voteAverage;
    int movieID;
    String genre;


    public MovieModal(int id, String title, String posterPath, String genre, double voteAverage) {
        this.movieID = id;
        this.title = title;
        this.posterPath = posterPath;
        this.genre = genre;
        this.voteAverage = voteAverage;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getTitle() {
        return title;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public int getMovieID() {
        return movieID;
    }

    public String getGenre() {
        return genre;
    }
}


package ScreeningsPackage;

import java.util.ArrayList;

public class Theater {
  private ArrayList<Movie> Movies = new ArrayList<Movie>();
  private String theaterName;

  public Theater(String name) {
    this.theaterName = name;
  }

  public String getTheaterName() {
    return this.theaterName;
  }

  public void addMovie(String Name) {
    Movie newMovie = new Movie(Name);
    Movies.add(newMovie);
  }

  public void changeDesc(Integer pos, String desc) throws IndexOutOfBoundsException {
    try {
      Movie temp = Movies.get(pos - 1);
      temp.setDesc(desc);
      Movies.set(pos - 1, temp);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public void changePrice(Integer pos, Integer price) throws Exception {
    if (price <= 0) {
      throw new Exception("ERROR: Price must be a positive integer.");
    }
    try {
      Movie temp = Movies.get(pos - 1);
      temp.setPrice(price);
      Movies.set(pos - 1, temp);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public void changeAvailableSeats(Integer pos, Integer seats) throws Exception {
    if (seats < 0) {
      throw new Exception("ERROR: Number of available seats can't be negative.");
    }
    try {
      Movie temp = Movies.get(pos - 1);
      temp.setSeats(seats);
      Movies.set(pos - 1, temp);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public void printDetails(Integer pos) {
    Movie temp = Movies.get(pos - 1);
    System.out.println("Name: " + temp.getMovieName());
    System.out.println("Movie Description: " + temp.getDesc());
    System.out.println("Ticket price: " + temp.getPrice());
    System.out.println("Seats available: " + temp.getSeats());
  }

  public Integer getSeats(Integer pos) {
    return Movies.get(pos - 1).getSeats();
  }

  public Integer getPrice(Integer pos) {
    return Movies.get(pos - 1).getPrice();
  }

  public void printMovies() {
    Movie[] Movielist = new Movie[Movies.size()];
    Movies.toArray(Movielist);
    int len = Movielist.length;
    if (len == 0) {
      System.out.println("This theater currently has no scheduled shows.");
      return;
    }
    for (int i = 0; i < len; i++) {
      System.out.println((i + 1) + ". " + Movielist[i].getMovieName());
    }
  }

  public Integer getMovieCount() {
    return Movies.size();
  }
}

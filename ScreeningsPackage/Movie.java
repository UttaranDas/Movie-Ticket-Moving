package ScreeningsPackage;

public class Movie {
  private String name;
  private String descString;
  private Integer ticketPrice;
  private Integer seatsAvailable;

  Movie(String name) {
    this.name = name;
    this.descString = "";
    this.ticketPrice = 100;
    this.seatsAvailable = 10;
  }

  public void setDesc(String desc) {
    this.descString = desc;
  }

  public void setPrice(Integer p) {
    this.ticketPrice = p;
  }

  public void setSeats(Integer s) {
    this.seatsAvailable = s;
  }

  public String getMovieName() {
    return this.name;
  }

  public String getDesc() {
    return this.descString;
  }

  public Integer getPrice() {
    return this.ticketPrice;
  }

  public Integer getSeats() {
    return this.seatsAvailable;
  }
}

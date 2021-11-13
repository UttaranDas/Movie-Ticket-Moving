import Users.Base;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Reader.FileReader;
import ScreeningsPackage.Theater;
import Users.Admin;
import Users.User;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Welcome.");
    System.out.println("Please enter your username to identify yourself.");
    String user = sc.nextLine();
    Base LoggedIn;
    if (user.equals("admin")) {
      LoggedIn = new Admin();
    } else {
      LoggedIn = new User(user);
    }

    ArrayList<String> theater1 = FileReader.readStrings("Old Theater.txt");
    ArrayList<String> theater2 = FileReader.readStrings("New Theater.txt");

    Theater[] TheaterList = new Theater[2];
    TheaterList[0] = new Theater(theater1.get(0));
    TheaterList[1] = new Theater(theater2.get(0));

    for(Integer i = 1; i < theater1.size(); i++) {
      TheaterList[0].addMovie(theater1.get(i));
    }
    for(Integer i = 1; i < theater2.size(); i++) {
      TheaterList[1].addMovie(theater2.get(i));
    }

    System.out.println("Welcome " + LoggedIn.getName() + "!");
    while (true) {
      Integer actionSelected = -1;
      System.out.println("What would you like to do?");
      while (actionSelected == -1) {
        System.out.println("Please pick a valid option.");
        Integer op;
        System.out.println("1. Book movie ticket(s).");
        System.out.println("2. Manage scheduled Movies data (Requires administrator privileges).");
        op = sc.nextInt();
        if (op == 1) {
          actionSelected = 1;
        } else if (op == 2) {
          try {
            if (LoggedIn.getPrivileges()) {
              actionSelected = 2;
            } else {
              throw new Exception("You do not have the required privileges.");
            }
          } catch (Exception e) {
            actionSelected = -1;
            System.out.println(e);
          }
        }
      }

      if (actionSelected == 1) {
        Integer th = -1;
        while (th == -1) {
          System.out.println("Please select a valid theater.");
          for (Integer i = 0; i < TheaterList.length; i++) {
            System.out.println((i + 1) + " " + TheaterList[i].getTheaterName());
          }
          try {
            th = sc.nextInt();
            th = th - 1;
            if (th >= TheaterList.length || th < 0) {
              throw new Exception("The selected theater does not exist.");
            }

          } catch (InputMismatchException e) {
            System.out.println(e);
            th = -1;
            sc.next();
          }
          catch (Exception e) {
            th = -1;
            System.out.println(e);
          }
        }
        Integer mchoice = -1;
        while (mchoice == -1) {
          System.out.println("Please select a valid movie available at this theater.");
          TheaterList[th].printMovies();
          try {
            mchoice = sc.nextInt();
            if (mchoice <= 0 || mchoice > TheaterList[th].getMovieCount()) {
              throw new Exception("The selected movie does not exist");
            }
            if (TheaterList[th].getSeats(mchoice) == 0) {
              throw new Exception("Sorry, This screening is fully booked.");
            }
          } catch (Exception e) {
            mchoice = -1;
            System.out.println(e);
            sc.next();
          }
        }
        System.out.println("The details of the movie are as follows:");
        TheaterList[th].printDetails(mchoice);
        System.out.println("How many tickets would you like to purchase?");
        Integer tickets = -1;
        while (tickets == -1) {
          System.out.println("Please select a valid number of seats.");
          try {
            tickets = sc.nextInt();
            if (tickets > TheaterList[th].getSeats(mchoice)) {
              throw new Exception("The entered number of seats are not available.");
            }
            if (tickets < 1) {
              throw new Exception("You must purchase atleast one ticket.");
            }
          } catch (Exception e) {
            tickets = -1;
            System.out.println(e);
            sc.next();
          }
        }
        System.out.println("That would cost " + (tickets * TheaterList[th].getPrice(mchoice)) + " rupees");
        System.out.println("Would you like to confirm your purchase?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        Integer purchaseChoice = -1;
        while (purchaseChoice == -1) {
          System.out.println("Please choose a valid option.");
          try {
            purchaseChoice = sc.nextInt();
            if (purchaseChoice == 1) {
              System.out.println("Thank you for your purchase.");
              TheaterList[th].changeAvailableSeats(mchoice, TheaterList[th].getSeats(mchoice) - tickets);
              break;
            }
            if (purchaseChoice == 2) {
              System.out.println("Understood. You may restart the process if you wish to.");
            } else {
              throw new Exception("Incorrect choice");
            }
          } catch (Exception e) {
            purchaseChoice = -1;
            System.out.println(e);
            sc.next();
          }
        }
        String continueQuery = "placeHolder";
        System.out.println("Enter 'q' to quit the program, or 'c' to continue viewing.");
        while (!(continueQuery.equals("q") || continueQuery.equals("c"))) {
          continueQuery = sc.nextLine();
        }
        if (continueQuery.equals("q")) {
          sc.close();
          return;
        }
      } else {
        Integer th = -1;
        while (th == -1) {
          System.out.println("Please select a valid theater.");
          for (Integer i = 0; i < TheaterList.length; i++) {
            System.out.println((i + 1) + " " + TheaterList[i].getTheaterName());
          }
          try {
            th = sc.nextInt();
            th = th - 1;
            if (th >= TheaterList.length || th < 0) {
              throw new Exception("The selected theater does not exist.");
            }

          } catch (Exception e) {
            th = -1;
            System.out.println(e);
            sc.next();
          }
        }
        Integer mchoice = -1;
        while (mchoice == -1) {
          System.out.println("Please select a valid movie available at this theater.");
          TheaterList[th].printMovies();
          try {
            mchoice = sc.nextInt();
            if (mchoice <= 0 || mchoice > TheaterList[th].getMovieCount()) {
              throw new Exception("The selected movie does not exist");
            }
          } catch (Exception e) {
            mchoice = -1;
            System.out.println(e);
            sc.next();
          }
        }
        System.out.println("The details of the movie are as follows:");
        TheaterList[th].printDetails(mchoice);
        System.out.println("How would you like to proceed?");
        System.out.println("1. Change/add movie description.");
        System.out.println("2. Change ticket price for this movie.");
        System.out.println("3. Change number of available seats for this movie.");
        System.out.println();
        Integer changeChoice = -1;
        while (changeChoice == -1) {
          System.out.println("Please select a valid option");
          try {
            changeChoice = sc.nextInt();
            if (changeChoice != 1 && changeChoice != 2 && changeChoice != 3) {
              throw new Exception("ERROR: Invalid choice");
            }
          } catch (Exception e) {
            changeChoice = -1;
            System.out.println(e);
            sc.next();
          }
        }

        switch (changeChoice) {
        case 1:
          System.out.println("Please enter a single line description of the movie.");
          sc.nextLine();
          String description = sc.nextLine();
          TheaterList[th].changeDesc(mchoice, description);
          break;
        case 2:
          Integer price = -1;
          while (price < 0) {
            System.out.println("Please enter the new ticket price for this movie.");
            price = sc.nextInt();
            try {
              TheaterList[th].changePrice(mchoice, price);
            } catch (Exception e) {
              price = -1;
              System.out.println(e);
              sc.next();
            }
          }
          break;
        case 3:
          Integer seats = -1;
          while (seats < 0) {
            System.out.println("Please enter the number of available seats for this movie.");
            seats = sc.nextInt();
            try {
              TheaterList[th].changeAvailableSeats(mchoice, seats);
            } catch (Exception e) {
              seats = -1;
              System.out.println(e);
              sc.next();
            }
          }
        default:
          break;
        }
        System.out.println("Changes registered. New details are as follows:");
        TheaterList[th].printDetails(mchoice);
        System.out.println();
        String continueQuery = "placeHolder";
        System.out.println("Enter 'q' to quit the program, or 'c' to continue viewing.");
        while (!(continueQuery.equals("q") || continueQuery.equals("c"))) {
          continueQuery = sc.nextLine();
        }
        if (continueQuery.equals("q")) {
          sc.close();
          return;
        }
      }
    }
  }
}
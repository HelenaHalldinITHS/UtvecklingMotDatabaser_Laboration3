package se.iths.helena;


import java.sql.SQLException;
import java.util.Scanner;

public class ArtistApplication {
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ArtistApplication application = new ArtistApplication();
        application.start();
    }

    private void start() {
        ArtistDao artistDao = new ArtistDaoImpl();

        while (true){
         printMenu();
         int choice = getChoice();
         if (choice == 0)
             break;
         executeChoice(choice);
        }
    }

    private void executeChoice(int i){
        switch (i){
            case 1 -> addArtist();
        }
    }

    private void addArtist() {
        System.out.println("enter the artist's id:");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("enter the artist's first name: ");
        String firstName = scanner.nextLine();
    }

    private void printMenu() {
        System.out.println("""
                Choose one of the following (by writing its corresponding number):
                1. Add an artist
                2. Remove an artist
                3. Update an artist
                4. Show all artists
                5. Find artist by id
                6. Find artist by age
                7. Find artist by name
                0. End application
                """);
    }
    private int getChoice(){
        return Integer.parseInt(scanner.nextLine());
    }
}

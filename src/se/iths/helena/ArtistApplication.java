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
         executeChoice(choice,artistDao);
        }
    }

    private void executeChoice(int i, ArtistDao artistDao){
        switch (i){
            case 1 -> addArtist(artistDao);
            case 2 -> removeArtist(artistDao);
            case 3 -> updateArtistAge(artistDao);
        }
    }

    private void updateArtistAge(ArtistDao artistDao) {
        System.out.println("enter id of the artist you want to update: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("enter the artist's new age: ");
        int age = Integer.parseInt(scanner.nextLine());
        artistDao.findById(id).ifPresentOrElse(artist -> artistDao.update(artist,age), () -> System.out.println("there is no current artist with id " + id));
    }

    private void removeArtist(ArtistDao artistDao) {
        System.out.println("enter id of the artist you want to remove: ");
        int id = Integer.parseInt(scanner.nextLine());
        artistDao.findById(id).ifPresentOrElse(artistDao::delete, () -> System.out.println("there is no current artist with id " + id));
    }

    private void addArtist(ArtistDao artistDao) {
        System.out.println("enter the artist's id:");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("enter the artist's first name: ");
        String firstName = scanner.nextLine();
        System.out.println("enter the artist's last name: ");
        String lastName = scanner.nextLine();
        System.out.println("enter the artist's age:");
        int age = Integer.parseInt(scanner.nextLine());
        artistDao.add(new Artist(id,firstName,lastName,age));
    }

    private void printMenu() {
        System.out.println("""
                Choose one of the following (by writing its corresponding number):
                1. Add an artist
                2. Remove an artist
                3. Update an artist's age
                4. Update an artist's lastname
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

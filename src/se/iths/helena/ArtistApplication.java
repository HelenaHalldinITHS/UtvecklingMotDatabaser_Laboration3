package se.iths.helena;


import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ArtistApplication {
    Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        ArtistApplication application = new ArtistApplication();
        application.start();
    }

    private void start() {
        ArtistDao artistDao = new ArtistDaoImpl();

        while (true) {
            printMenu();
            int choice = getChoice();
            if (!executeChoice(choice, artistDao))
                break;
        }
    }

    private boolean executeChoice(int i, ArtistDao artistDao) {
        boolean continueApplication = true;
        switch (i) {
            case 1 -> addArtist(artistDao);
            case 2 -> removeArtist(artistDao);
            case 3 -> updateArtistAge(artistDao);
            case 4 -> updateArtistName(artistDao);
            case 5 -> showAllArtists(artistDao);
            case 6 -> findArtistById(artistDao);
            case 7 -> findArtistByAge(artistDao);
            case 8 -> findArtistByName(artistDao);
            case 0 -> continueApplication = false;
        }
        return continueApplication;
    }

    private void findArtistByName(ArtistDao artistDao) {
        System.out.println("enter the last name of the artist/artists you want to find: ");
        String lastName = scanner.nextLine();
        List <Artist> artists = artistDao.findByName(lastName);
        if (artists.isEmpty())
            System.out.println("there is no current artist with the last name: " + lastName);
        else {
            artists.forEach(this::print);
        }
        System.out.println();
    }

    private void findArtistByAge(ArtistDao artistDao) {
        System.out.println("enter the age of the artist/artists you want to find: ");
        int age = Integer.parseInt(scanner.nextLine());
        List <Artist> artists = artistDao.findByAge(age);
        if (artists.isEmpty())
            System.out.println("there is no current artist with age: " + age);
        else {
            artists.forEach(this::print);
        }
        System.out.println();
    }

    private void findArtistById(ArtistDao artistDao) {
        System.out.println("enter id of the artist you want to find: ");
        int id = Integer.parseInt(scanner.nextLine());
        artistDao.findById(id).ifPresentOrElse(this::print,() -> System.out.println("there is no current artist with id " + id));
        System.out.println();
    }

    private void print(Artist artist){
        System.out.println("id: " + artist.getId() +
                ", first name: " + artist.getFirstName() +
                ", last name: " + artist.getLastName() +
                ", age: " + artist.getAge());
    }
    private void showAllArtists(ArtistDao artistDao) {
        artistDao.showAll();
        System.out.println();
    }

    private void updateArtistName(ArtistDao artistDao) {
        System.out.println("enter id of the artist you want to update: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("enter the artist's new last name: ");
        String lastName = scanner.nextLine();
        artistDao.findById(id).ifPresentOrElse(artist -> artistDao.update(artist, lastName), () -> System.out.println("there is no current artist with id " + id));
        System.out.println();
    }

    private void updateArtistAge(ArtistDao artistDao) {
        System.out.println("enter id of the artist you want to update: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("enter the artist's new age: ");
        int age = Integer.parseInt(scanner.nextLine());
        artistDao.findById(id).ifPresentOrElse(artist -> artistDao.update(artist, age), () -> System.out.println("there is no current artist with id " + id));
        System.out.println();
    }

    private void removeArtist(ArtistDao artistDao) {
        System.out.println("enter id of the artist you want to remove: ");
        int id = Integer.parseInt(scanner.nextLine());
        artistDao.findById(id).ifPresentOrElse(artistDao::delete, () -> System.out.println("there is no current artist with id " + id));
        System.out.println();
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
        artistDao.add(new Artist(id, firstName, lastName, age));
        System.out.println();
    }

    private void printMenu() {
        System.out.println(""" 
                Choose one of the following (by writing its corresponding number):
                1. Add an artist
                2. Remove an artist
                3. Update an artist's age
                4. Update an artist's last name
                5. Show all artists
                6. Find artist by id
                7. Find artist by age
                8. Find artist by last name
                0. End application
                """);
    }

    private int getChoice() {
        return Integer.parseInt(scanner.nextLine());
    }
}

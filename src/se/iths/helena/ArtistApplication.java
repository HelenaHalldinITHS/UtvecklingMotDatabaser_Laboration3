package se.iths.helena;


import java.sql.SQLException;

public class ArtistApplication {
    public static void main(String[] args) {
        ArtistApplication application = new ArtistApplication();
        application.start();
    }

    private void start() {
        ArtistDao artistDao = new ArtistDaoImpl();
    }

}

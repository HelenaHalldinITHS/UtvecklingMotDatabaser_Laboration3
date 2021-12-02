package se.iths.helena;


import java.sql.SQLException;

public class ArtistApplication {
    public static void main(String[] args) {
        ArtistApplication application = new ArtistApplication();
        application.start();
    }

    private void start() {
        ArtistDao artistDao = new ArtistDaoImpl();
        /*
        artistDao.add(new Artist(1,"Helena","Halldin",23));
        artistDao.add(new Artist(2,"Tobias","Halldin",24));
        artistDao.add(new Artist(3,"Sara","Olsson",23));
        artistDao.delete(new Artist(3,"Sara","Olsson",23));
        artistDao.update(new Artist(2,"Tobias","Halldin",24),25);
        artistDao.update(new Artist(2,"Tobias","Halldin",25), "Eklund");
         */
        artistDao.showAll();


    }

}

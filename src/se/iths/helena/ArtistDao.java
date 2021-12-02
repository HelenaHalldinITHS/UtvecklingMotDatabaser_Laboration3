package se.iths.helena;

import java.util.List;
import java.util.Optional;

public interface ArtistDao {
    void add(Artist artist);

    void delete(Artist artist);

    void update(Artist artist, int age);

    void update(Artist artist, String lastName);

    void showAll();

    Optional<Artist> findById(int id);

    List<Artist> findByAge(int age);

    List<Artist> findByName(String lastName);
}

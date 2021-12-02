package se.iths.helena;

import java.util.List;
import java.util.Optional;

public class ArtistDaoImpl implements ArtistDao{

    @Override
    public void add(Artist artist) {

    }

    @Override
    public void delete(Artist artist) {

    }

    @Override
    public void update(Artist artist, int age) {

    }

    @Override
    public void update(Artist artist, String lastName) {

    }

    @Override
    public void showAll() {

    }

    @Override
    public Optional<Artist> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Artist> findByAge(int age) {
        return null;
    }

    @Override
    public List<Artist> findByName(String lastName) {
        return null;
    }
}

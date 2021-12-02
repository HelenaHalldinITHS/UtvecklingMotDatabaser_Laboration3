package se.iths.helena;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArtistDaoImpl implements ArtistDao {
    private Connection connection;

    public ArtistDaoImpl() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/laboration3", "helena", "Password123");
            createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTable() throws SQLException {
        Statement statement = connection.createStatement();
        try {
            statement.executeUpdate(
                    "CREATE TABLE Artist (" +
                            "id int," +
                            "first_name varchar(50)," +
                            "last_name varchar(50)," +
                            "age smallint," +
                            "primary key(id)" +
                            " );");
        } catch (SQLSyntaxErrorException ignored) {
        }
    }

    @Override
    public void add(Artist artist) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Artist (id, first_name, last_name, age) VALUES (?,?,?,?)");
            statement.setInt(1, artist.getId());
            statement.setString(2, artist.getFirstName());
            statement.setString(3, artist.getLastName());
            statement.setInt(4, artist.getAge());
            statement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("id " + artist.getId() + " is unavailable");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void delete(Artist artist) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Artist WHERE id = ?");
            statement.setInt(1, artist.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Artist artist, int age) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE Artist SET age = ? WHERE id = ?");
            statement.setInt(1, age);
            statement.setInt(2, artist.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Artist artist, String lastName) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE Artist SET last_name = ? WHERE id = ?");
            statement.setString(1, lastName);
            statement.setInt(2, artist.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Artist");
            print(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Artist> findById(int id) {
        Optional<Artist> artist = Optional.empty();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Artist WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                artist = Optional.of(new Artist(id, resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4)));
            }
            print(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artist;
    }

    @Override
    public List<Artist> findByAge(int age) {
        List<Artist> artists = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Artist WHERE age = ?");
            statement.setInt(1, age);
            ResultSet resultSet = statement.executeQuery();
            artists = convertResultSetToList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artists;
    }

    @Override
    public List<Artist> findByName(String lastName) {
        List<Artist> artists = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Artist WHERE last_name = ?");
            statement.setString(1, lastName);
            ResultSet resultSet = statement.executeQuery();
            artists = convertResultSetToList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artists;
    }

    private List<Artist> convertResultSetToList(ResultSet resultSet) throws SQLException {
        List<Artist> artists = new ArrayList<>();
        while (resultSet.next()) {
            artists.add(new Artist(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4)));
        }
        return artists;
    }

    private void print(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            System.out.println("id: " + resultSet.getString(1) +
                    ", first name: " + resultSet.getString(2) +
                    ", last name: " + resultSet.getString(3) +
                    ", age: " + resultSet.getString(4));
        }
    }
}

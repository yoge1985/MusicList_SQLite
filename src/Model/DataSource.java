package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataSource {

    public static final String DB_NAME = "music.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\family gefen\\Downloads\\musicExtract\\" + DB_NAME;

    public static final String TABLE_ALBUMS = "albums";
    public static final String COLUMN_ALBUM_ID = "_id";
    public static final String COLUMN_ALBUM_NAME = "name";
    public static final String COLUMN_ALBUM_ARTIST = "artist";

    public static final String TABLE_ARTISTS = "artists";
    public static final String COLUMN_ARTIST_ID = "_id";
    public static final String COLUMN_ARTIST_NAME = "name";

    public static final String TABLE_SONGS = "songs";
    public static final String COLUMN_SONG_TRACK = "track";
    public static final String COLUMN_SONG_TITLE = "title";
    public static final String COLUMN_SONG_ALBUM = "album";

    private Connection conn;

    public boolean open() {

        try {

            conn = DriverManager.getConnection(CONNECTION_STRING);
            return true;
        } catch (SQLException e) {
            System.out.println("could not open connection: " + e.getMessage());
            return false;
        }
    }

    public void close() {

        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("couldn't close connection: " + e.getMessage());
        }

    }

    public List<Artist> queryArtists() {

        try(Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM " + TABLE_ARTISTS)) {

            List<Artist> artists = new ArrayList<>();
            while (result.next()){
                Artist artist = new Artist();
                artist.setId(result.getInt(COLUMN_ARTIST_ID));
                artist.setName(result.getString(COLUMN_ARTIST_NAME));
                artists.add(artist);
            }
            return artists;


        } catch (SQLException e) {
            System.out.println("could not find query " + e.getMessage());
            return null;
        }


    }
}

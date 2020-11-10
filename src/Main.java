import Model.Artist;
import Model.DataSource;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        DataSource dataSource = new DataSource();

            if (!dataSource.open()){
                System.out.println("could not open");
                return;
            }

        List<Artist> artists = dataSource.queryArtists();

        for (Artist artist: artists){
            System.out.println("artist id: " + artist.getId() + " artist name: " + artist.getName());
        }

            dataSource.close();



    }
}

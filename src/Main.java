import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Load player data from local file upon program start
        System.out.println("Loading Player Data...");
        FileIO.getPlayers();
        Management.playerList.addAll(FileIO.playerData);
        System.out.println("Player Data loaded successfully");

        // Populate different Management ArrayLists using playerData

        // Upload player data to local file
        FileIO.uploadPlayers();

    }
}
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Load player data from local file upon program start
        System.out.println("Loading Player Data...");
        FileIO.getPlayers();
        FileIO.initializePlayers();
        FileIO.getGameData();
        FileIO.getLog();

        // Create a shutdown hook, preventing loss of data by accident
        Runtime.getRuntime().addShutdownHook(new Thread(){
            public void run(){
                System.out.println("Uploading Player Data.");
                FileIO.uploadPlayers();
                System.out.println("Player Data uploaded.");
                System.out.println("Uploading History");
                FileIO.uploadHistory();
                System.out.println("History Uploaded");
                System.out.println("Uploading Game Data.");
                FileIO.uploadGameData();
                System.out.println("Game data uploaded.");
                System.out.println("Uploading Log.");
                FileIO.uploadLog();
                System.out.println("Log Uploaded.");
                System.out.println();
                System.out.println("All Data Saved, program closing.");
            }
        });

        // Create user interface object
        UserInterface UI = new UserInterface();

        UI.menu();
    }
}
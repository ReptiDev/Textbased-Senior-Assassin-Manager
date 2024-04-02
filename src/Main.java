import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Load player data from local file upon program start
        System.out.println("Loading Player Data...");
        FileIO.getPlayers();
        FileIO.initializePlayers();
        FileIO.getData();

        // Create a shutdown hook, preventing loss of data by accident
        Runtime.getRuntime().addShutdownHook(new Thread(){
            public void run(){
                System.out.println("Uploading data...");
                FileIO.uploadPlayers();
                System.out.println("Players uploaded.");
                FileIO.uploadData();
                System.out.println("Game data uploaded.");
                System.out.println("Uploading complete, program closing.");
            }
        });

        // Create user interface object
        UserInterface UI = new UserInterface();

        UI.menu();


    }

    private static void testCase()
    {
        for (int i = 0; i < 5; i++)
        {
            new Assassin("" + i, new Team("Team " + i));
        }
    }
}
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Load player data from local file upon program start
        System.out.println("Loading Player Data...");
        FileIO.getPlayers();
        Management.playerList.addAll(FileIO.playerData);

        // Populate different Management ArrayLists using playerData
        for (int i = 0; i < Management.playerList.size(); i++)
        {
            Assassin player = Management.playerList.get(i);
            Team team = Management.playerList.get(i).getTeam();

            if (player.isAlive())
            {
                Management.alivePlayers.add(player);
            }
            if (!player.isAlive())
            {
                Management.deadPlayers.add(player);
            }
            if (!Management.teamList.contains(team))
            {
                Management.teamList.add(team);
            }
            if (team.isAlive() && !Management.aliveTeams.contains(team))
            {
                Management.aliveTeams.add(team);
            }
            if (!team.isAlive() && !Management.deadTeams.contains(team))
            {
                Management.deadTeams.add(team);
            }
        }

        // Create a shutdown hook, preventing loss of data by accident
        Runtime.getRuntime().addShutdownHook(new Thread(){
            public void run(){
                System.out.println("Uploading data...");
                FileIO.uploadPlayers();
                System.out.println("Data uploaded, program closing.");
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
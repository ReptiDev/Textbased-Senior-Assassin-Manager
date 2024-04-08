import java.io.*;
import java.util.ArrayList;

public class FileIO {
    static ArrayList<Assassin> playerData = new ArrayList<Assassin>();

    // need to add another file where normal values are stored (right now just round number)

    public static void getPlayers() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("playerdata.tmp"));
            Object temp;
            while ((temp = in.readObject()) != null) {
                playerData.add((Assassin) temp);
            }
            in.close();
        }
        catch (Exception ex)
        {
            if (ex instanceof EOFException) {
                System.out.println("End of file reached!");
                System.out.println("# Players Found: " + playerData.size() + "\n");
            } else if (ex instanceof FileNotFoundException) {
                System.out.println("File not found!");
            }
        }
    }

    public static void initializePlayers()
    {
        Management.playerList.addAll(playerData);

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
    }
    public static void uploadPlayers()
    {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("playerdata.tmp"));
            for(int i = 0; i < Management.playerList.size(); i++)
            {
                out.writeObject(Management.playerList.get(i));
            }
            out.close();
        }
        catch (Exception ex)
        {
            if (ex instanceof FileNotFoundException) {
                System.out.println("File not found!");
            }
        }
    }

    // Line 1 = Round Number
    // Line 2 = Game Over (true/false)
    public static void uploadGameData()
    {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("gamedata.txt"));
            writer.write("" + Management.round);
            writer.close();
        }
        catch (IOException e)
        {
            System.out.println("Something fucked up uploading data.");
        }

    }

    public static void getGameData()
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("gamedata.txt"));
            Management.round = Integer.parseInt(reader.readLine());
        }
        catch (IOException e)
        {
            System.out.println("Something messed up getting data.");
        }
    }

    public static void uploadLog()
    {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("gamelog.txt"));
            for (String event : LogHandler.log)
            {
                writer.write(event + "\n");
            }
            writer.close();
        }
        catch (IOException e)
        {
            System.out.println("Log couldn't be uploaded.");
        }
    }

    public static void getLog()
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("gamelog.txt"));
            String logLine = reader.readLine();
            while(logLine != null) {
                LogHandler.log.add(logLine);
                logLine = reader.readLine();
            }
            reader.close();
        }
        catch (IOException e)
        {
            System.out.println("Log couldn't be retrieved.");
        }
    }
}





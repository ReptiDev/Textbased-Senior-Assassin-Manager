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
}





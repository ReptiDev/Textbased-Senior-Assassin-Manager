import java.io.*;
import java.util.ArrayList;

public class FileIO {
    static ArrayList<Assassin> playerData = new ArrayList<Assassin>();

    public static void getPlayers() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("data.tmp"));
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
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.tmp"));
            for(int i = 0; i < Management.playerList.size(); i++)
            {
                out.writeObject(Management.playerList.get(i));
            }
        }
        catch (Exception ex)
        {
            if (ex instanceof FileNotFoundException) {
                System.out.println("File not found!");
            }
        }
    }
}

    //public static void uploadPlayers() throws IOException {
    //    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.tmp"));
    //   out.writeObject(new Assassin("Bob", new Team("The Cookers")));
    //    out.writeObject(new Assassin("Tim", new Team("The Murderer")));
    //    out.close();
   // }



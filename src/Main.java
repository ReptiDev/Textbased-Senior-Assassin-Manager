import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Hello world!");
        System.out.println("Uploading players");
        //FileIO.uploadPlayers();
        System.out.println("Finished uploading.");
        FileIO.getPlayers();
        for (Assassin player : FileIO.playerData)
        {
            System.out.println(player.getName());
            System.out.println(player.getTeam().getName());
        }
    }
}
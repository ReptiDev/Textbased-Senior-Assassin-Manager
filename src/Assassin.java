import java.util.ArrayList;
import java.io.Serializable;

public class Assassin implements Serializable
{
    private String name;
    private boolean isAlive;
    private int killCount;
    private Team team;
    private ArrayList<Assassin> killList;


    public Assassin(String name, Team team)
    {
        this.name = name;
        this.team = team;
        this.isAlive = true;
        Management.playerList.add(this);
        Management.alivePlayers.add(this);
    }

    public static ArrayList<Assassin> getPlayers()
    {
        return Management.playerList;
    }

    public static ArrayList<Assassin> getAlive()
    {
        return Management.alivePlayers;
    }

    public static ArrayList<Assassin> getDead()
    {
        return Management.deadPlayers;
    }

    public String getName()
    {
        return name;
    }

    public Team getTeam()
    {
        return team;
    }

    public boolean isAlive()
    {
        return isAlive;
    }

    public int getKillCount()
    {
        return killCount;
    }

    public ArrayList<Assassin> getKillList()
    {
        return killList;
    }

    public void kill(Assassin target)
    {
        // Update player stats with their kill
        this.killList.add(target);
        this.killCount++;

        // Remove person killed from corresponding lists of players, add to dead list
        for (int i = 0; i < Management.alivePlayers.size(); i++)
        {
            if(Management.alivePlayers.get(i).equals(target))
            {
                target.isAlive = false;
                Management.alivePlayers.remove(i);
                break;
            }
        }
        Management.deadPlayers.add(target);

        // Check if target's death causes their whole team to die
        // If it does, set them as dead, add/remove from respective lists, and assign the dead team's target to the team who killed them
        if (!target.team.getMembers()[0].isAlive() && !target.team.getMembers()[1].isAlive())
        {
            target.team.setAlive(false);
            for (int i = 0; i < Team.getAliveTeams().size(); i++)
            {
                if(Team.getAliveTeams().get(i).equals(target.team))
                {
                    Team.getAliveTeams().remove(i);
                    break;
                }
            }
            Team.getDeadTeams().add(target.team);

            Management.assignFromKill(this, target);
            System.out.println("This kill fully eliminated team " + target.team.getName() + ". This team's new target is: " + this.team.getTarget());
        }
        else
        {
            System.out.println("A player on the other team is still alive. Inform the group their target has not changed.");
        }


    }
}

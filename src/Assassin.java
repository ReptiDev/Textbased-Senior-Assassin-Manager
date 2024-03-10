import java.util.ArrayList;
import java.io.Serializable;

public class Assassin implements Serializable
{
    private final String name;
    private final Team team;
    private boolean isAlive;
    private boolean inRound;
    private int killCount;
    private ArrayList<Assassin> killList;


    public Assassin(String name, Team team)
    {
        this.name = name;
        this.team = team;
        this.isAlive = true;
        this.team.addMember(this);
        Management.playerList.add(this);
        Management.alivePlayers.add(this);
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

    public String toString()
    {
        return this.name + "(" + this.team.getName() + ")";
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
            for (int i = 0; i < Management.aliveTeams.size(); i++)
            {
                if(Management.aliveTeams.get(i).equals(target.team))
                {
                    Management.aliveTeams.remove(i);
                    break;
                }
            }
            Management.deadTeams.add(target.team);

            Management.assignFromKill(this, target);
            System.out.println("This kill fully eliminated team " + target.team.getName() + ". This team's new target is: " + this.team.getTarget());
        }
        else
        {
            System.out.println("A player on the other team is still alive. Inform the group their target has not changed.");
        }


    }
}

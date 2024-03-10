import java.util.ArrayList;
import java.io.Serializable;


public class Team implements Serializable
{


    private String name;
    private Assassin[] members = new Assassin[Management.teamMax];

    private boolean isAlive;
    private Team targetTeam;

    public Team(String name)
    {
        this.name = name;
        this.isAlive = true;
        Management.teamList.add(this);
        Management.aliveTeams.add(this);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Team getTarget()
    {
        return targetTeam;
    }

    public void setTarget(Team target)
    {
        this.targetTeam = target;
    }

    public boolean isAlive()
    {
        return isAlive;
    }

    public void setAlive(boolean isAlive)
    {
        this.isAlive = isAlive;
    }

    public String toString()
    {
        return this.name;
    }

    public Assassin[] getMembers()
    {
        return members;
    }

    public void addMember(Assassin member)
    {
        boolean success = false;
        for (int i = 0; i < members.length; i++)
        {
            if (members[i] == null)
            {
                members[i] = member;
                success = true;
            }
        }
        if (!success)
        {
            System.out.println("Could not add player to team, it is already full.");
        }
    }

    public int getKillCount()
    {
        return -1;
    }
}

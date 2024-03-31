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

    public String getTargetName()
    {
        return targetTeam.getName() + "(" + targetTeam.members[0].getName() + ", " + targetTeam.members[1].getName() + ")";
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

    public void eliminate()
    {
        this.isAlive = false;
        Management.aliveTeams.remove(this);
        Management.deadTeams.add(this);
    }

    public String toString()
    {
        return this.name + "\n" + members[0].getName() + ": Alive = " + members[0].isAlive() + "\n" + members[1].getName() + ": Alive = " + members[1].isAlive() + "\n";
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
                break;
            }
        }

        if (!success)
        {
            System.out.println("Could not add player to team, it is already full.");
        }
    }

    public int getTotalKills()
    {
        return members[0].getKillCount() + members[1].getKillCount();
    }

    public int getKillsThisRound()
    {
        return members[0].getKillsThisRound() + members[1].getKillsThisRound();
    }
}

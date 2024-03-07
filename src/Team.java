import java.util.ArrayList;
import java.io.Serializable;


public class Team implements Serializable
{


    private String name;
    private Assassin p1;
    private Assassin p2;

    private boolean isAlive;
    private Team targetTeam;

    public Team(String name)
    {
        this.name = name;
        this.isAlive = true;
        Management.teamList.add(this);
        Management.aliveTeams.add(this);
    }

    public static ArrayList<Team> getTeamList()
    {
        return Management.teamList;
    }

    public static ArrayList<Team> getAliveTeams()
    {
        return Management.aliveTeams;
    }

    public static ArrayList<Team> getDeadTeams()
    {
        return Management.deadTeams;
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

    public Assassin[] getMembers()
    {
        Assassin[] temp = {p1,p2};
        return temp;
    }

    public int getKillCount()
    {
        return p1.getKillCount() + p2.getKillCount();
    }
}

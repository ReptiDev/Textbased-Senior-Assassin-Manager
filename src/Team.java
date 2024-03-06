import java.util.ArrayList;

public class Team
{
    private static ArrayList<Team> teamList;
    private static ArrayList<Team> aliveTeams;
    private static ArrayList<Team> deadTeams;

    private String name;
    private Assassin p1;
    private Assassin p2;

    private boolean isAlive;
    private Team targetTeam;

    public Team(Assassin p1, Assassin p2)
    {
        this.p1 = p1;
        this.p2 = p2;
        this.isAlive = true;
        teamList.add(this);
        aliveTeams.add(this);
    }

    public static ArrayList<Team> getTeamList()
    {
        return teamList;
    }

    public static ArrayList<Team> getAliveTeams()
    {
        return aliveTeams;
    }

    public static ArrayList<Team> getDeadTeams()
    {
        return deadTeams;
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

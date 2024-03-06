import java.util.ArrayList;

public class Management
{
    public static void assignFromKill(Assassin player, Assassin target)
    {
        player.getTeam().setTarget(target.getTeam().getTarget());
        target.getTeam().setTarget(null);
    }

    public static void assignWeekly()
    {
        ArrayList<Team> unassigned = new ArrayList<Team>();
        ArrayList<Team> noTarget = new ArrayList<Team>();

        // Loop through all teams currently alive
        // Unassign all targets, add all teams to unassigned list
        for (int i = 0; i < Team.getAliveTeams().size(); i++)
        {
            Team.getAliveTeams().get(i).setTarget(null);
            unassigned.add(Team.getAliveTeams().get(i));
            noTarget.add(Team.getAliveTeams().get(i));
        }

        // Loop through unassigned list, assign teams randomly with following procedure
        // Choose a random index ahead of the current index by saying Math.random() * (array.size() - current index + 1)
        // Assign the random
    }
}

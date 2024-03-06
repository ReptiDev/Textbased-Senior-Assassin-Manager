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
        // Assign the random
        for (int i = 0; i < noTarget.size(); i++)
        {
            int random = (int)(Math.random() * (unassigned.size()));
            noTarget.get(i).setTarget(unassigned.get(random));
            unassigned.remove(random);
        }

        public static void printTargets()
        {
            for(int i = 0; i < Team.getAliveTeams().size(); i++)
            {
                System.out.print("Team: " + Team.getAliveTeams().get(i).getName());
                System.out.println("(" + Team.getAliveTeams().get(i).getMembers()[0].getName() + ", " + Team.getAliveTeams().get(i).getMembers()[1] + ")");
                System.out.print("Target: " + Team.getAliveTeams().get(i).getTarget().getName());
                System.out.println("(" + Team.getAliveTeams().get(i).getTarget().getMembers()[0].getName() + ", " + Team.getAliveTeams().get(i).getTarget().getMembers()[1] + ")");
                System.out.println();
            }
        }
    }
}

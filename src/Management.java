import java.util.ArrayList;

public class Management
{
    public static final int teamMax = 2;
    public static int round;
    public static ArrayList<Assassin> playerList = new ArrayList<Assassin>();
    public static ArrayList<Assassin> alivePlayers = new ArrayList<Assassin>();
    public static ArrayList<Assassin> deadPlayers = new ArrayList<Assassin>();
    public static ArrayList<Team> teamList = new ArrayList<Team>();
    public static ArrayList<Team> aliveTeams = new ArrayList<Team>();
    public static ArrayList<Team> deadTeams = new ArrayList<Team>();
    public static Team winner = null;

    public static void assignFromKill(Assassin player, Assassin target)
    {
        player.getTeam().setTarget(target.getTeam().getTarget());
        target.getTeam().setTarget(null);

        if(checkWin())
        {
            System.out.println("Team " + winner.getName() + " has won Assassins.");
        }
    }

    public static void clearLists()
    {
        playerList.clear();
        alivePlayers.clear();
        deadPlayers.clear();
        teamList.clear();
        aliveTeams.clear();
        deadTeams.clear();
    }
    public static void randomAssignment()
    {
        ArrayList<Team> unassigned = new ArrayList<Team>();
        ArrayList<Team> noTarget = new ArrayList<Team>();

        // Loop through all teams currently alive
        // Unassign all targets, add all teams to unassigned list
        for (int i = 0; i < aliveTeams.size(); i++) {
            aliveTeams.get(i).setTarget(null);
            unassigned.add(aliveTeams.get(i));
            noTarget.add(aliveTeams.get(i));
        }

        // Loop through unassigned list, assign teams randomly with following procedure
        // Assign the random
        for (int i = 0; i < noTarget.size(); i++) {
            while (true)
            {
                int random = (int) (Math.random() * (unassigned.size()));
                if (unassigned.get(random).getTarget() != null)
                {
                    if (unassigned.get(random).getTarget().equals(noTarget.get(i)))
                    {
                        break;
                    }
                }
                if (unassigned.get(random) != noTarget.get(i))
                {
                    noTarget.get(i).setTarget(unassigned.get(random));
                    unassigned.remove(random);
                    break;
                }

            }

        }
    }
    public static void startGame()
    {
        round = 1;
        randomAssignment();
    }
    public static void endRound() {
        // Loop through all teams and checks if they got a kill this round
        // If they did, they move through to the next
        // If not, both members are eliminated and the team is out
        String message;

        for (int i = 0; i < aliveTeams.size(); i++)
        {
            Team team = aliveTeams.get(i);
            Assassin member1 = team.getMembers()[0];
            Assassin member2 = team.getMembers()[1];

            System.out.println("Currently inspecting team " + team);


            if(team.getImmune())
            {
                message = "Team " + team.getName() + " is immune from elimination this round.";
                LogHandler.addLog(message);
                team.setImmune(false);
                member1.setKillsThisRound(0);
                member2.setKillsThisRound(0);
            }

            if (team.getKillsThisRound() == 0)
            {
                team.eliminate();
                member1.eliminate();
                member2.eliminate();
                i--;
                message = "Team " + team.getName() + " was eliminated due to not getting a kill in Round " + round;
                LogHandler.addLog(message);
            }
            else {
                message = "Team " + team.getName() + " successfully moves forward to the next round.";
                LogHandler.addLog(message);
                member1.setKillsThisRound(0);
                member2.setKillsThisRound(0);
            }
        }

        randomAssignment();
        round++;
        message = "Round " + round + " has begun";
        LogHandler.addLog(message);

        if(checkWin())
        {
            System.out.println("Team " + winner.getName() + " has won Assassins.");
        }
    }

    private static boolean checkWin()
    {
        if (aliveTeams.size() == 1)
        {
            winner = aliveTeams.get(0);
            return true;
        }
        return false;
    }

}

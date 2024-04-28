import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    public static Scanner scanner = new Scanner(System.in);
    public static int menuChoice = 0;

    public static void menu() {
        menuChoice = 0;
        while (true) {
            System.out.println("Senior Assassin | Current Round: " + Management.round);
            System.out.println("(1) Team Manager");
            System.out.println("(2) Player Manager");
            System.out.println("(3) Game Manager");
            System.out.println("(4) Stats");
            System.out.println("(5) Lists");
            System.out.println("(6) Import Data From History");
            System.out.println("(7) Close Program");

            menuChoice = scanner.nextInt();
            scanner.nextLine();

            switch (menuChoice) {
                case 1:
                    teamManager();
                    break;
                case 2:
                    playerManager();
                    break;
                case 3:
                    roundControl();
                    break;
                case 4:
                    stats();
                    break;
                case 5:
                    printLists();
                    break;
                case 6:
                    dataFromHistory();
                    break;
                case 7:
                    System.exit(0);
            }
        }
    }

    public static void dataFromHistory()
    {
        System.out.println("Saved Histories:");

        File[] files = new File("history/").listFiles();
        for(int i = 0; i < files.length; i++)
        {
            System.out.println("(" + (i+1) + ")" + files[i].getName());
        }

        System.out.println("Enter the number of the history you would like to load from.");
        int num = scanner.nextInt();
        scanner.nextLine();
        System.out.println("You want to load from the file: " + files[num-1]);
        System.out.println("Are you sure? (Y/N)");
        String input = scanner.nextLine();
        if (input.equals("Y"))
        {
            FileIO.loadFromHistory(files[num-1]);
            LogHandler.addLog("A past history has been loaded. Some of the above events may not be accurate.");
        }
    }

    public static void stats() {
        menuChoice = 0;
        while (true) {
            System.out.println("(1) Get Player Kills");
            System.out.println("(2) Get Team Kills");
            System.out.println("(3) Most Kills");
            System.out.println("(4) Back");

            menuChoice = scanner.nextInt();
            scanner.nextLine();

            switch (menuChoice) {
                case 1:
                    getPlayerKills();
                    break;
                case 2:
                    getTeamKills();
                    break;
                case 3:
                    mostKills();
                    break;
                case 4:
                    menu();
                    break;
            }
        }
    }

    public static void getPlayerKills()
    {
        System.out.println("Enter a player's name: ");
        String playerName = scanner.nextLine();
        for (Assassin player : Management.playerList)
        {
            if(player.getName().toLowerCase().contains(playerName.toLowerCase()))
            {
                System.out.println("This player has " + player.getKillCount() + " kills. They have killed the following people:");
                System.out.println(player.getKillList());
            }
        }
    }

    public static void getTeamKills()
    {
        System.out.println("Enter a team's name: ");
        String teamName = scanner.nextLine();
        for (Team team : Management.teamList)
        {
            if(team.getName().toLowerCase().contains(teamName.toLowerCase()))
            {
                System.out.println("This team has " + team.getTotalKills() + " kills. They have killed the following people:");
                System.out.println(team.getTeamKillList());
            }
        }
    }

    public static void mostKills()
    {
        ArrayList<Team> most = new ArrayList<Team>();
        most.add(Management.teamList.get(0)) ;

        for(int i = 1; i < Management.teamList.size(); i++)
        {
            Team currentTeam = Management.teamList.get(i);
            if(currentTeam.getTotalKills() > most.get(0).getTotalKills())
            {
                most.clear();
                most.add(currentTeam);
            }
            else if(currentTeam.getTotalKills() == most.get(0).getTotalKills())
            {
                most.add(currentTeam);
            }
        }

        System.out.println("The following teams have the most kills with " + most.get(0).getTotalKills() + " kills.");
        for(int i = 0; i < most.size(); i++)
        {
            System.out.println(most.get(i).getName() + "(" + most.get(i).getMembers()[0].getName() + " " + most.get(i).getMembers()[1].getName() + ")");

        }
    }

    public static void teamManager() {
        menuChoice = 0;
        while (true) {
            System.out.println("(1) Edit Team");
            System.out.println("(2) Add Team");
            System.out.println("(3) Delete Team");
            System.out.println("(4) Back");

            menuChoice = scanner.nextInt();
            scanner.nextLine();

            switch (menuChoice) {
                case 1:
                    editTeam();
                    break;
                case 2:
                    System.out.println("Press enter to stop adding teams at any time");
                    addTeam();
                    break;
                case 3:
                    deleteTeam();
                    break;
                case 4:
                    menu();
                    break;
            }
        }
    }
    public static void addTeam() {
        String input;
        String teamName;
        String memberOneName;
        String memberTwoName;
        int count = 0;

        while (true)
        {
            System.out.println("Enter the Team Name:");
            input = scanner.nextLine();
            if (input.equals(""))
            {
                break;
            }
            teamName = input.strip();

            System.out.println("Enter the first member:");
            input = scanner.nextLine();
            if (input.equals(""))
            {
                break;
            }
            memberOneName = input.strip();

            System.out.println("Enter the second member:");
            input = scanner.nextLine();
            if (input.equals(""))
            {
                break;
            }
            memberTwoName = input.strip();

            Team team = new Team(teamName);
            new Assassin(memberOneName, team);
            new Assassin(memberTwoName, team);
            count++;
        }

        System.out.println("Made " + count + " new teams");
        menu();
    }

    public static void deleteTeam()
    {
        String input;
        String teamName;

        System.out.println("Enter the Team Name:");
        input = scanner.nextLine();
        if (input.equals(""))
        {
            return;
        }
        teamName = input;

        for(int i = 0; i < Management.teamList.size(); i++)
        {
            Team team = Management.teamList.get(i);
            if (team.getName().equals(teamName))
            {
                System.out.println("Team with that name found. Are you sure you want to delete them? (y/n)");
                input = scanner.nextLine();
                if (input.equals("y"))
                {
                    Assassin[] members = team.getMembers();
                    for (int j = 0; j < members.length; j++)
                    {
                        System.out.println("Removing " + members[j].getName());
                        Management.playerList.remove(members[j]);
                        Management.alivePlayers.remove(members[j]);
                    }
                    System.out.println("Removing " + team.getName());
                    Management.teamList.remove(team);
                    Management.aliveTeams.remove(team);
                    System.out.println("Team and Players successfully deleted.");
                }
            }
        }
    }

    public static void editTeam() {
        menuChoice = 0;
        while (true) {
            System.out.println("Enter the name of the team you want to edit - or the name of a member:");
            String input = scanner.nextLine();
            Team selectedTeam = null;

            BreakPoint:
            for(Team team: Management.teamList)
            {
                if (team.getName().equalsIgnoreCase(input))
                {
                    selectedTeam = team;
                    break;
                }
                else
                {
                    for (Assassin player : team.getMembers())
                    {
                        if (player.getName().equalsIgnoreCase(input))
                        {
                            selectedTeam = team;
                            break BreakPoint;
                        }
                    }
                }
            }

            if (selectedTeam == null)
            {
                System.out.println("No team or players with that name.");
                menu();
                break;
            }

            System.out.println("Editing " + selectedTeam.getName());
            System.out.println("(1) Toggle Immune (Current:" + selectedTeam.getImmune() + ")");
            System.out.println("(2) Change Name");
            System.out.println("(3) Back");

            menuChoice = scanner.nextInt();
            scanner.nextLine();

            switch (menuChoice) {
                case 1:
                    selectedTeam.setImmune(!selectedTeam.getImmune());
                    System.out.println("Team Immune? " + selectedTeam.getImmune());
                    menu();
                    break;
                case 2:
                    System.out.println("Enter the new name:");
                    input = scanner.nextLine();
                    selectedTeam.setName(input);
                    menu();
                    break;
                case 3:
                    menu();
                    break;
            }
        }
    }

    public static void playerManager() {
        menuChoice = 0;
        while (true) {
            System.out.println("(1) Log Kill");
            System.out.println("(2) Revive Player");
            System.out.println("(3) Manual Elimination");
            System.out.println("(4) Check Alive");
            System.out.println("(5) Back");

            menuChoice = scanner.nextInt();
            scanner.nextLine();

            switch (menuChoice) {
                case 1:
                    logKill();
                    break;
                case 2:
                    revivePlayer();
                    break;
                case 3:
                    manualElimination();
                    break;
                case 4:
                    checkAlive();
                    break;
                case 5:
                    menu();
                    break;
            }
        }
    }

    public static void checkAlive()
    {
        System.out.println("Enter the player name:");
        String input = scanner.nextLine();

        for (Assassin player : Management.playerList)
        {
            if (player.getName().equalsIgnoreCase(input))
            {
                System.out.println(player.isAlive());
                return;
            }
        }
        System.out.println("No player found with that name");
    }
    public static void logKill() {
        Assassin killer = null;
        Assassin target = null;

        System.out.println("Enter the killer's name: ");
        String killerName = scanner.nextLine();
        if (killerName.isEmpty())
        {
            menu();
        }
        boolean success = false;
        for (int i = 0; i < Management.alivePlayers.size(); i++) {
            if (Management.alivePlayers.get(i).getName().toLowerCase().contains(killerName.toLowerCase())) {
                killer = Management.alivePlayers.get(i);
                success = true;
            }
        }

        if (!success) {
            System.out.println("No player exists with that name. Retry");
            menu();
        }

        System.out.println("Enter the target's name: ");
        String targetName = scanner.nextLine();

        success = false;
        for (int i = 0; i < Management.alivePlayers.size(); i++) {
            if (Management.alivePlayers.get(i).getName().toLowerCase().contains(targetName.toLowerCase())) {
                target = Management.alivePlayers.get(i);
                success = true;
            }
        }

        if (!success) {
            System.out.println("No player exists with that name. Retry.");
            menu();
        }


        success = false;
        for(Assassin player: killer.getTeam().getTarget().getMembers())
        {
            if (player.equals(target)) {
                success = true;
                break;
            }
        }

        if (success)
        {
            String message = "Player " + target.getName() + " has been eliminated by " + killer.getName();
            LogHandler.addLog(message);
            killer.kill(target);
        }
        else
        {
            System.out.println("Stated target is not on the target team of the killer");
        }
    }

    public static void revivePlayer()
    {
        System.out.println("Enter the player name:");
        String input = scanner.nextLine();

        for (Assassin player : Management.playerList)
        {
            if (player.getName().equalsIgnoreCase(input))
            {
                player.revive();
                return;
            }
        }
        System.out.println("No player found with that name");
    }

    public static void manualElimination()
    {
        System.out.println("Enter the player name:");
        String input = scanner.nextLine();

        for (Assassin player : Management.playerList)
        {
            if (player.getName().equalsIgnoreCase(input))
            {
                player.eliminate();
                System.out.println(player.getName() + " has been eliminated");
                LogHandler.addLog("The above elimination was manually done.");
                return;
            }
        }

        System.out.println("No player found with that name");
    }

    public static void printLists()
    {
        menuChoice = 0;
        while (true) {
            System.out.println("(1) All Teams");
            System.out.println("(2) Alive Teams");
            System.out.println("(3) Dead teams");
            System.out.println("(4) Target List");
            System.out.println("(5) Back");

            menuChoice = scanner.nextInt();
            scanner.nextLine();

            switch (menuChoice) {
                case 1:
                    printAllTeams();
                    break;
                case 2:
                    printAliveTeams();
                    break;
                case 3:
                    printDeadTeams();
                    break;
                case 4:
                    printTargets();
                    break;
                case 5:
                    menu();
                    break;
            }
        }
    }

    public static void printAllTeams()
    {
        for (Team team : Management.teamList)
        {
            System.out.println(team);
        }
    }

    public static void printAliveTeams()
    {
        for (Team team : Management.aliveTeams)
        {
            System.out.println(team);
        }
    }

    public static void printDeadTeams()
    {
        for (Team team : Management.deadTeams)
        {
            System.out.println(team);
        }
    }
    public static void printTargets()
    {
        for(int i = 0; i < Management.aliveTeams.size(); i++)
        {
            try {
                Team team = Management.aliveTeams.get(i);
                System.out.print("Team: " + team.getName());
                System.out.println("(" + team.getMembers()[0].getName() + ", " + team.getMembers()[1].getName() + ")");
                System.out.println("Targets: " + team.getTarget().getName());
                Assassin player1 = team.getTarget().getMembers()[0];
                Assassin player2 = team.getTarget().getMembers()[1];
                if (player1.isAlive())
                {
                    System.out.println(player1.getName() + "(Alive)");
                }
                else
                {
                    System.out.println(player1.getName() + "(Dead)");
                }
                if (player2.isAlive())
                {
                    System.out.println(player2.getName() + "(Alive)");
                }
                else
                {
                    System.out.println(player2.getName() + "(Dead)");
                }
                System.out.println();
            }
            catch (NullPointerException e)
            {
                System.out.println("Team doesn't have a target.");
            }
        }
    }

    public static void roundControl()
    {
        menuChoice = 0;
        while (true) {
            System.out.println("(1) Start Game");
            System.out.println("(2) Next Round");
            System.out.println("(3) Back");

            menuChoice = scanner.nextInt();
            scanner.nextLine();

            switch (menuChoice) {
                case 1:
                    Management.startGame();
                    menu();
                    break;
                case 2:
                    Management.endRound();
                    menu();
                    break;
                case 3:
                    menu();
                    break;
            }
        }
    }

}

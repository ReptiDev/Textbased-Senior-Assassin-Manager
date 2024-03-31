import java.util.Scanner;

public class UserInterface {
    public static Scanner scanner = new Scanner(System.in);
    public static int currentInput = 0;

    public static void menu() {
        currentInput = 0;
        while (true) {
            System.out.println("Senior Assassin | Current Round: " + Management.round);
            System.out.println("(1) Add Team");
            System.out.println("(2) Log Kill");
            System.out.println("(3) Round Control");
            System.out.println("(4) Show Stats");
            System.out.println("(5) Print Lists");
            System.out.println("(6) Close Program");

            currentInput = scanner.nextInt();
            scanner.nextLine();

            switch (currentInput) {
                case 1:
                    addTeam();
                    break;
                case 2:
                    logKill();
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
                    System.exit(0);
            }
        }
    }

    public static void stats() {
        System.out.println("To do");
    }

    public static void addTeam() {
        String teamName;
        String memberOneName;
        String memberTwoName;

        System.out.println("Enter the Team Name:");
        teamName = scanner.nextLine();

        System.out.println("Enter the first member:");
        memberOneName = scanner.nextLine();
        System.out.println("Enter the second member:");
        memberTwoName = scanner.nextLine();

        Team team = new Team(teamName);
        new Assassin(memberOneName, team);
        new Assassin(memberTwoName, team);

        menu();
    }

    public static void logKill() {
        Assassin killer = null;
        Assassin target = null;

        System.out.println("Enter the killer's name: ");
        String killerName = scanner.nextLine();

        boolean success = false;
        for (int i = 0; i < Management.alivePlayers.size(); i++) {
            if (Management.alivePlayers.get(i).getName().contains(killerName)) {
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
            if (Management.alivePlayers.get(i).getName().contains(targetName)) {
                target = Management.alivePlayers.get(i);
                success = true;
            }
        }

        if (!success) {
            System.out.println("No player exists with that name. Retry.");
            menu();
        }

        killer.kill(target);
    }

    public static void printLists()
    {
        currentInput = 0;
        while (true) {
            System.out.println("(1) All Teams");
            System.out.println("(2) Alive Teams");
            System.out.println("(3) Dead teams");
            System.out.println("(4) Target List");
            System.out.println("(5) Back");

            currentInput = scanner.nextInt();
            scanner.nextLine();

            switch (currentInput) {
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
            Team team = Management.aliveTeams.get(i);
            System.out.print("Team: " + team.getName());
            System.out.println("(" + team.getMembers()[0].getName() + ", " + team.getMembers()[1].getName() + ")");
            System.out.print("Target: " + team.getTarget().getName());
            System.out.println("(" + team.getTarget().getMembers()[0].getName() + ", " + team.getTarget().getMembers()[1].getName() + ")\n");
        }
    }

    public static void roundControl()
    {
        currentInput = 0;
        while (true) {
            System.out.println("(1) Start Game");
            System.out.println("(2) Next Round");
            System.out.println("(3) Back");

            currentInput = scanner.nextInt();
            scanner.nextLine();

            switch (currentInput) {
                case 1:
                    Management.startGame();
                    break;
                case 2:
                    Management.endRound();
                    break;
                case 3:
                    menu();
                    break;
            }
        }
    }

}

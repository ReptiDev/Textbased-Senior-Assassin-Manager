import java.util.Scanner;

public class UserInterface
{
    public static Scanner scanner = new Scanner(System.in);
    public static int currentInput = 0;
    public static void menu()
    {
        while (true)
        {
            System.out.println("Senior Assassin");
            System.out.println("(1) ?");
            System.out.println("(2) Show Stats");
            System.out.println("(3) Print Targets");
            System.out.println("(4) Close Program");

            currentInput = scanner.nextInt();

            switch (currentInput)
            {
                case 1:
                    System.out.println("Hi");
                case 2:
                    stats();
                    break;
                case 3:
                    printTargets();
                    break;
                case 4:
                    System.exit(0);
            }
        }
    }

    public static void stats()
    {
        System.out.println("To do");
    }

    public static void printTargets()
    {
        for(int i = 0; i < Management.aliveTeams.size(); i++)
        {
            Team team = Management.aliveTeams.get(i);
            System.out.print("Team: " + team.getName());
            System.out.println("(" + team.getMembers()[0].getName() + ", " + team.getMembers()[1] + ")");
            System.out.print("Target: " + team.getTarget().getName());
            System.out.println("(" + team.getTarget().getMembers()[0].getName() + ", " + team.getTarget().getMembers()[1] + ")\n");
            System.out.println("");
        }
        menu();
    }

    // What other menu options would there be?
}

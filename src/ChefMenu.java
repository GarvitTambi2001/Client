import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ChefMenu {

    public static void displayChefMenu(BufferedReader stdIn, PrintWriter out, BufferedReader in) throws IOException {
        String choice = "";
        while (!choice.equals("5")) {
            System.out.println("Chef Menu:");
            System.out.println("1. View Food Menu");
            System.out.println("2. View Top Recommendations");
            System.out.println("3. Roll Out Next Day Menu");
            System.out.println("4. View Voted Report");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = stdIn.readLine();

            switch (choice) {
                case "1":
                    out.println("VIEW_MENU_REQUEST");
                    String viewResponse = in.readLine();
                    System.out.println(viewResponse);
                    break;
                case "2":
                    String numberOfRecommendation = promptString(stdIn, "Enter number of recommendation you want: ");
                    out.println("VIEW_TOP_RECOMMENDATIONS;"+numberOfRecommendation);
                    String recommendationsResponse = in.readLine();
                    System.out.println(recommendationsResponse);
                    break;
                case "3":
                    rollOutNextDayMenu(stdIn, out, in);
                    break;
                case "4":
                    out.println("VIEW_VOTED_REPORT");
                    String votedReportResponse = in.readLine();
                    System.out.println(votedReportResponse);
                    break;
                case "5":
                    System.out.print("Enter your Employee Id: ");
                    String employeeId = stdIn.readLine();
                    ClientCafeteria.sendUserSessionRequest(out, employeeId, "logout");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static String promptString(BufferedReader stdIn, String prompt) throws IOException {
        System.out.print(prompt);
        return stdIn.readLine();
    }

    private static void rollOutNextDayMenu(BufferedReader stdIn, PrintWriter out, BufferedReader in) throws IOException {
        System.out.print("Enter the MenuIds for the next day (comma separated): ");
        String menuIds = stdIn.readLine();
        out.println("ROLLOUT_NEXT_DAY_MENU_REQUEST;" + menuIds);

        String response = in.readLine();
        if (response != null && response.startsWith("ROLLOUT_NEXT_DAY_MENU_RESPONSE")) {
            String[] parts = response.split(";");
            if ("SUCCESS".equals(parts[1])) {
                System.out.println("Next day's menu rolled out successfully.");
            } else {
                System.out.println("Failed to roll out next day's menu.");
            }
        }
    }
}

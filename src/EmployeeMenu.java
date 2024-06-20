import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class EmployeeMenu {

    public static void displayEmployeeMenu(BufferedReader stdIn, PrintWriter out, BufferedReader in) throws IOException {
        System.out.println("Employee Menu:");
        System.out.println("1. Vote for Next Day Recommendation");
        System.out.println("2. Give Feedback to Chef");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
        String choice = stdIn.readLine();

        switch (choice) {
            case "1":
                voteForRecommendation(stdIn, out, in);
                break;
            case "2":
                // Add code for feedback to chef here
                break;
            case "3":
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    private static void voteForRecommendation(BufferedReader stdIn, PrintWriter out, BufferedReader in) throws IOException {
        out.println("VIEW_CHEF_RECOMMENDATIONS");
        String response = in.readLine();

        if (response != null && response.startsWith("VIEW_RECOMMENDATIONS_RESPONSE")) {
            String[] parts = response.split(";");
            System.out.println("Chef Recommendations:");
            for (int i = 1; i < parts.length; i += 3) {
                System.out.println("MenuId: " + parts[i] + ", Name: " + parts[i + 1] + ", VoteCount: " + parts[i + 2]);
            }

            System.out.print("Enter the MenuIds to vote for (comma separated): ");
            String menuIds = stdIn.readLine();
            out.println("VOTE_RECOMMENDATION_REQUEST;" + menuIds);

            String voteResponse = in.readLine();
            if (voteResponse != null && voteResponse.startsWith("VOTE_RECOMMENDATION_RESPONSE")) {
                String[] voteParts = voteResponse.split(";");
                if ("SUCCESS".equals(voteParts[1])) {
                    System.out.println("Votes registered successfully.");
                } else {
                    System.out.println("Failed to register votes.");
                }
            }
        }
    }
}

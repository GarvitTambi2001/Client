import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class EmployeeMenu {

    public static void displayEmployeeMenu(BufferedReader stdIn, PrintWriter out, BufferedReader in) throws IOException {
        String choice = "";
        while(!choice.equals("3")){
            System.out.println("Employee Menu:");
            System.out.println("1. Vote for Next Day Recommendation");
            System.out.println("2. Give Feedback to Chef");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = stdIn.readLine();

            switch (choice) {
                case "1":
                    voteForRecommendation(stdIn, out, in);
                    break;
                case "2":
                    giveFeedbackToChef(stdIn, out, in);
                    break;
                case "3":
                    System.out.print("Enter your Employee Id: ");
                    String employeeId = stdIn.readLine();
                    ClientCafeteria.sendUserSessionRequest(out, employeeId, "logout");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static void voteForRecommendation(BufferedReader stdIn, PrintWriter out, BufferedReader in) throws IOException {
        out.println("VIEW_CHEF_RECOMMENDATIONS");
        String response = in.readLine();

        if (response != null && response.startsWith("VIEW_RECOMMENDATIONS_RESPONSE")) {
            String[] parts = response.split(";");
            System.out.println("Chef Recommendations:");
            for (int i = 1; i < parts.length; i++) {
                System.out.println(parts[i]);
            }
            System.out.print("Enter your Employee Id: ");
            String employeeId = stdIn.readLine();
            System.out.print("Enter the MenuIds to vote for (comma separated): ");
            String menuIds = stdIn.readLine();
            out.println("VOTE_RECOMMENDATION_REQUEST;" + menuIds + ";" + employeeId);

            String voteResponse = in.readLine();
            if (voteResponse != null && voteResponse.startsWith("VOTE_RECOMMENDATION_RESPONSE")) {
                String[] voteParts = voteResponse.split(";");
                if ("SUCCESS".equals(voteParts[1])) {
                    System.out.println("Votes registered successfully.");
                } else {
                    System.out.println("Failed to submit feedback : " + voteParts[2]);
                }
            }
        }
    }

        private static void giveFeedbackToChef (BufferedReader stdIn, PrintWriter out, BufferedReader in) throws
        IOException {
            System.out.print("Enter your EmployeeId: ");
            String employeeId = stdIn.readLine();

            System.out.print("Enter the MenuId to give feedback: ");
            int menuId = Integer.parseInt(stdIn.readLine());

            System.out.print("Enter your comment: ");
            String comment = stdIn.readLine();

            System.out.print("Enter your rating: ");
            int rating = Integer.parseInt(stdIn.readLine());

            Feedback feedbackDTO = new Feedback();
            feedbackDTO.setEmployeeId(employeeId);
            feedbackDTO.setMenuId(menuId);
            feedbackDTO.setComment(comment);
            feedbackDTO.setRating(rating);

            Gson gson = new Gson();
            String jsonFeedback = gson.toJson(feedbackDTO);
            out.println("GIVE_FEEDBACK_REQUEST;" + jsonFeedback);

            String feedbackResponse = in.readLine();
            if (feedbackResponse != null && feedbackResponse.startsWith("GIVE_FEEDBACK_RESPONSE")) {
                String[] feedbackParts = feedbackResponse.split(";");
                if ("SUCCESS".equals(feedbackParts[1])) {
                    System.out.println("Feedback submitted successfully.");
                } else {
                    System.out.println("Failed to submit feedback : " + feedbackParts[2]);
                }
            }
        }
    }

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

class GiveFeedbackToChefCommand implements MenuCommand {
    private final BufferedReader stdIn;
    private final PrintWriter out;
    private final BufferedReader in;

    public GiveFeedbackToChefCommand(BufferedReader stdIn, PrintWriter out, BufferedReader in) {
        this.stdIn = stdIn;
        this.out = out;
        this.in = in;
    }

    @Override
    public void execute() throws IOException {
        String employeeId = promptString("Enter your EmployeeId: ");
        int menuId = Integer.parseInt(promptString("Enter the MenuId to give feedback: "));
        String comment = promptString("Enter your comment: ");
        int rating = Integer.parseInt(promptString("Enter your rating: "));

        Feedback feedbackDTO = new Feedback();
        feedbackDTO.setEmployeeId(employeeId);
        feedbackDTO.setMenuId(menuId);
        feedbackDTO.setComment(comment);
        feedbackDTO.setRating(rating);

        Gson gson = new Gson();
        String jsonFeedback = gson.toJson(feedbackDTO);
        out.println(Constants.GIVE_FEEDBACK_REQUEST + jsonFeedback);

        String feedbackResponse = in.readLine();
        if (feedbackResponse != null && feedbackResponse.startsWith(Constants.GIVE_FEEDBACK_RESPONSE)) {
            handleFeedbackResponse(feedbackResponse);
        }
    }

    private void handleFeedbackResponse(String feedbackResponse) {
        String[] feedbackParts = feedbackResponse.split(";");
        if (Constants.SUCCESS.equals(feedbackParts[1])) {
            System.out.println("Feedback submitted successfully.");
        } else {
            System.out.println("Failed to submit feedback: " + feedbackParts[2]);
        }
    }

    private String promptString(String prompt) throws IOException {
        System.out.print(prompt);
        return stdIn.readLine();
    }
}

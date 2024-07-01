import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

class ViewTopRecommendationsCommand implements MenuCommand {
    private final BufferedReader stdIn;
    private final PrintWriter out;
    private final BufferedReader in;

    public ViewTopRecommendationsCommand(BufferedReader stdIn, PrintWriter out, BufferedReader in) {
        this.stdIn = stdIn;
        this.out = out;
        this.in = in;
    }

    @Override
    public void execute() throws IOException {
        String numberOfRecommendation = promptString("Enter number of recommendation you want: ");
        out.println(Constants.VIEW_TOP_RECOMMENDATIONS + numberOfRecommendation);
        String recommendationsResponse = in.readLine();
        System.out.println(recommendationsResponse);
    }

    private String promptString(String prompt) throws IOException {
        System.out.print(prompt);
        return stdIn.readLine();
    }
}
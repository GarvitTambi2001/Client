import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
class ViewVotedReportCommand implements MenuCommand {
    private final PrintWriter out;
    private final BufferedReader in;

    public ViewVotedReportCommand(PrintWriter out, BufferedReader in) {
        this.out = out;
        this.in = in;
    }

    @Override
    public void execute() throws IOException {
        out.println(Constants.VIEW_VOTED_REPORT);
        String votedReportResponse = in.readLine();
        System.out.println(votedReportResponse);
    }
}
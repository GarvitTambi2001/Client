import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
class DiscardMenuCommand implements MenuCommand {
    private final PrintWriter out;
    private final BufferedReader in;
    private final BufferedReader stdIn;

    private final String role;

    public DiscardMenuCommand(PrintWriter out, BufferedReader in, BufferedReader stdIn, String role) {
        this.out = out;
        this.in = in;
        this.stdIn = stdIn;
        this.role = role;
    }

    @Override
    public void execute() throws IOException {
        DiscardManager discardManager = new DiscardManager(out, in, stdIn);
        discardManager.handleUserActions(role);
    }
}
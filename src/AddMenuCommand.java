import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
class AddMenuCommand implements MenuCommand {
    private final BufferedReader stdIn;
    private final PrintWriter out;
    private final BufferedReader in;

    public AddMenuCommand(BufferedReader stdIn, PrintWriter out, BufferedReader in) {
        this.stdIn = stdIn;
        this.out = out;
        this.in = in;
    }

    @Override
    public void execute() throws IOException {
        MenuManager.addMenu(stdIn, out, in);
    }
}
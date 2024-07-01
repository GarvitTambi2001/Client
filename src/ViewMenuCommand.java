import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

class ViewMenuCommand implements MenuCommand {
    private final PrintWriter out;
    private final BufferedReader in;

    public ViewMenuCommand(PrintWriter out, BufferedReader in) {
        this.out = out;
        this.in = in;
    }

    @Override
    public void execute() throws IOException {
        out.println("VIEW_MENU_REQUEST");
        String viewResponse = in.readLine();
        System.out.println(viewResponse);
    }
}
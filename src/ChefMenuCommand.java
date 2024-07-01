import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


public class ChefMenuCommand implements RoleMenuCommand {
    @Override
    public void execute(BufferedReader stdIn,PrintWriter out, BufferedReader in) throws IOException {
        ChefMenu chefMenu = new ChefMenu(out,in,stdIn);
        chefMenu.displayChefMenu();
    }
}
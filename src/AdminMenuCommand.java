import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


public class AdminMenuCommand implements RoleMenuCommand {
    @Override
    public void execute(BufferedReader stdIn,PrintWriter out, BufferedReader in) throws IOException {
        AdminMenu adminMenu = new AdminMenu(out,in,stdIn);
        adminMenu.displayAdminMenu();
    }
}
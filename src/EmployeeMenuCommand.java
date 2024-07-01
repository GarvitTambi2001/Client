import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


public class EmployeeMenuCommand implements RoleMenuCommand {
    @Override
    public void execute(BufferedReader stdIn,PrintWriter out, BufferedReader in) throws IOException {
        EmployeeMenu employeeMenu = new EmployeeMenu(out, in, stdIn);
        employeeMenu.displayEmployeeMenu();
    }
}
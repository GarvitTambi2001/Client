import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class AdminMenu {

    public static void displayAdminMenu(BufferedReader stdIn, PrintWriter out, BufferedReader in) throws IOException {
        String choice = "";
        while(!choice.equals("5")){
        System.out.println("Admin Menu:");
        System.out.println("1. View Food Menu");
        System.out.println("2. Add Item in Food Menu");
        System.out.println("3. Update Item in Food Menu");
        System.out.println("4. Delete Item in Food Menu");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
        choice = stdIn.readLine();

        switch (choice) {
            case "1":
                out.println("VIEW_MENU_REQUEST");
                String viewResponse = in.readLine();
                System.out.println(viewResponse);
                break;
            case "2":
                MenuManager.addMenu(stdIn, out, in);
                break;
            case "3":
                MenuManager.updateMenu(stdIn, out, in);
                break;
            case "4":
                MenuManager.deleteMenu(stdIn, out, in);
                break;
            case "5":
                System.out.print("Enter your Employee Id: ");
                String employeeId = stdIn.readLine();
                ClientCafeteria.sendUserSessionRequest(out, employeeId, "logout");
                break;
            default:
                System.out.println("Invalid choice");
           }
        }
    }
}

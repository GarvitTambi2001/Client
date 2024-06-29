import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientCafeteria {

    private static final String HOST = "localhost";
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

            login(out, in, stdIn);

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void login(PrintWriter out, BufferedReader in, BufferedReader stdIn) throws IOException {
        System.out.println("Enter your Employee ID:");
        String employeeId = stdIn.readLine();
        System.out.println("Enter your Password:");
        String password = stdIn.readLine();
        out.println("LOGIN_REQUEST;" + employeeId + ";" + password);

        String response = in.readLine();
        if (response != null) {
            String[] parts = response.split(";");
            String responseType = parts[0];

            if ("LOGIN_RESPONSE".equals(responseType)) {
                String status = parts[1];
                String message = parts[2];
                System.out.println(message);

                if ("SUCCESS".equals(status)) {
                    String role = parts[3];
                    sendUserSessionRequest(out, employeeId, "login");
                    displayRoleMenu(role, stdIn, out, in);
                } else {
                    System.out.println("Login failed");
                    System.exit(0);
                }
            }
        }
    }

    public static void sendUserSessionRequest(PrintWriter out, String employeeId, String requestType) throws IOException {
        UserSessionDTO sessionDTO = new UserSessionDTO();
        sessionDTO.setEmployeeId(employeeId);
        sessionDTO.setRequestType(requestType);

        Gson gson = new Gson();
        String jsonSession = gson.toJson(sessionDTO);
        out.println("USER_SESSION_REQUEST;" + jsonSession);
    }

    private static void displayRoleMenu(String role, BufferedReader stdIn, PrintWriter out, BufferedReader in) throws IOException {
        switch (role) {
            case "admin":
                AdminMenu.displayAdminMenu(stdIn, out, in);
                break;
            case "chef":
                ChefMenu.displayChefMenu(stdIn, out, in);
                break;
            case "employee":
                EmployeeMenu.displayEmployeeMenu(stdIn, out, in);
                break;
            default:
                System.out.println("Unknown role");
        }
    }
}

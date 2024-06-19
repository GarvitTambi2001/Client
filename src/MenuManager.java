import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

public class MenuManager {

    public static void addMenu(BufferedReader stdIn, PrintWriter out, BufferedReader in) throws IOException {
        MenuDTO menu = new MenuDTO();
        menu.setPrice(promptBigDecimal(stdIn, "Enter Prices: "));
        menu.setAvailabilityStatus("Yes");
        menu.setMealType(promptString(stdIn, "Enter Meal Type: "));
        menu.setScore(new BigDecimal(0)); // Initial score is zero

        Gson gson = new Gson();
        String json = gson.toJson(menu);
        out.println("ADD_MENU_REQUEST;" + json);
        System.out.println(in.readLine());
    }

    public static void updateMenu(BufferedReader stdIn, PrintWriter out, BufferedReader in) throws IOException {
        MenuDTO menu = new MenuDTO();
        menu.setMenuId(promptString(stdIn, "Enter Menu ID: "));
        menu.setPrice(promptBigDecimal(stdIn, "Enter Prices: "));
        menu.setAvailabilityStatus(promptString(stdIn, "Enter Availability Status (Yes/No): "));
        menu.setMealType(promptString(stdIn, "Enter Meal Type: "));
        menu.setScore(promptBigDecimal(stdIn, "Enter Score: "));

        Gson gson = new Gson();
        String json = gson.toJson(menu);
        out.println("UPDATE_MENU_REQUEST;" + json);
        System.out.println(in.readLine());
    }

    public static void deleteMenu(BufferedReader stdIn, PrintWriter out, BufferedReader in) throws IOException {
        String menuId = promptString(stdIn, "Enter Menu ID: ");
        out.println("DELETE_MENU_REQUEST;" + menuId);
        System.out.println(in.readLine());
    }

    private static String promptString(BufferedReader stdIn, String prompt) throws IOException {
        System.out.print(prompt);
        return stdIn.readLine();
    }

    private static BigDecimal promptBigDecimal(BufferedReader stdIn, String prompt) throws IOException {
        System.out.print(prompt);
        return new BigDecimal(stdIn.readLine());
    }
}

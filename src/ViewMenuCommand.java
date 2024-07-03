import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonPart = viewResponse.split(";")[1];
        System.out.println("Extracted JSON: " + jsonPart);
        jsonPart = jsonPart.trim();
        if (jsonPart.startsWith("{") && jsonPart.endsWith("}")) {
            jsonPart = jsonPart.substring(1, jsonPart.length() - 1).trim();
        }
        List<Menu> menuList = gson.fromJson(jsonPart, new TypeToken<List<Menu>>() {}.getType());
        String formattedJson = gson.toJson(menuList);
        System.out.println(formattedJson);;
    }
}
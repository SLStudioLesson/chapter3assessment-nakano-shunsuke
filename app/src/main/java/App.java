import com.recipeapp.datahandler.CSVDataHandler;
import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.datahandler.JSONDataHandler;
import com.recipeapp.ui.RecipeUI;
import java.io.*;

public class App {

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Choose the file format:");
            System.out.println("1. CSV");
            System.out.println("2. JSON");
            System.out.print("Select (1/2): ");
            String choice = reader.readLine();
            
            // DataHandler型の変数を作る
            DataHandler dataHandler;
            switch (Integer.parseInt(choice)) {
                case 1:
                    dataHandler = new CSVDataHandler();
                    break;
                case 2:
                    dataHandler = new JSONDataHandler();
                    break;
                default:
                    dataHandler = new CSVDataHandler();
                    break;
            }

            // MainMenuにデータを渡す
            mainMenu(dataHandler);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    // RecipeUIにデータを渡すメソッド
    public static void mainMenu(DataHandler dataHandler) {
        RecipeUI recipeMenu = new RecipeUI(dataHandler);
        recipeMenu.displayMenu();
    }
}
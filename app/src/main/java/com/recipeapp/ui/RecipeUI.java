package com.recipeapp.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;;

public class RecipeUI {
    private BufferedReader reader;
    private DataHandler dataHandler;

    public RecipeUI(DataHandler dataHandler) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        this.dataHandler = dataHandler;
    }
    
    public void displayMenu() {

        System.out.println("Current mode: " + dataHandler.getMode());

        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        displayRecipes();
                        break;
                    case "2":
                    addNewRecipe();
                        break;
                    case "3":
                        break;
                    case "4":
                        System.out.println("Exiting the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }

    // レシピデータを読み込み、コンソールに一覧表示
    public void displayRecipes()  throws IOException {
        // readDataメソッドを呼び出す
        ArrayList<Recipe> recipes = dataHandler.readData();
        // レシピデータが1件も存在しない場合
        if (recipes.isEmpty()) {
            System.out.println("No recipes available.");
            return;
        }

        // 読み込んだレシピデータを整形してコンソールに表示
        System.out.println("Recipes:");
        System.out.println("-----------------------------------");
        for (Recipe recipe : recipes) {
            // レシピ名
            System.out.println("Recipe Name: " + recipe.getName());
            // 具材名
            System.out.print("Main Ingredients: ");
            ArrayList<Ingredient> ingredients = recipe.getIngredients();
            for (int i = 0; i < ingredients.size(); i++) {
                System.out.print(ingredients.get(i).getName());
            }
            System.out.println();
            System.out.println("-----------------------------------");
        }
    }

    public void addNewRecipe() {
        try {
            // レシピ名を入力
            System.out.println();
            System.out.print("Enter recipe name: ");
            String name = reader.readLine();
            // 具材名を入力
            // doneを入力したかチェック
            boolean done = true;
            ArrayList<Ingredient> ingredients = new ArrayList<>();
            System.out.println("Enter ingredients (type 'done' when finished):");
            // doneと入力するまで入力を受け付け
            while (done) {
                System.out.print("Ingredient: ");
                String ingredientName = reader.readLine();
                // doneがあるか確認しあればループ終了
                if (ingredientName.equals("done")) {
                    done = false;
                }

                Ingredient ingredient = new Ingredient(ingredientName);
                ingredients.add(ingredient);
            }
    
            // Recipeインスタンスを作成
            Recipe newRecipe = new Recipe(name, ingredients);
    
            // dataHandlerのwriteDataメソッドにRecipeインスタンスを渡す
            dataHandler.writeData(newRecipe);

        } catch (IOException e) {
            //  IOExceptionを受け取った場合はFailed to add new recipe: 例外のメッセージとコンソールに表示
            System.out.println(e.getMessage());
        }

    }
}

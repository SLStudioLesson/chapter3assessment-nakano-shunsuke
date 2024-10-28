package com.recipeapp.datahandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

public class CSVDataHandler implements DataHandler {
    private String filePath;

    public CSVDataHandler() {
        this.filePath = "app/src/main/resources/recipes.csv";
    }

    public CSVDataHandler(String filePath) {
        this.filePath = filePath;
    }

    public String getMode() {
        return "CSV";
    }

    public ArrayList<Recipe> readData() throws IOException {
        ArrayList<Recipe> recipes = new ArrayList<>();
        // recipes.csvからレシピデータを読み込み
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // レシピ名と具材
            String line;
            // レシピ名
            String recipeName;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2);
                // レシピ名
                recipeName = parts[0];
                // 具材
                String[] ingredients = parts[1].split(",");
                ArrayList<Ingredient> recipeIngredients = new ArrayList<>();
                
                // 具材をひとつづつIngredientに変換してrecipeIngredientsに追加する
                for(String ingredient : ingredients) {
                    // Ingredient ingredientName = new Ingredient(ingredient);
                    recipeIngredients.add(new Ingredient(ingredient));
                }
                recipes.add(new Recipe(recipeName, recipeIngredients));
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        // リスト形式で返す
        return recipes;
    }

    public void writeData(Recipe recipe) throws IOException {
        // ファイルにアクセス
        try (PrintWriter print = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)))) {
            // レシピ名と具材の結合結果
            String ingredientsResult = recipe.getName();
            
            // 受け取ったレシピをカンマでつなぐ
            ArrayList<Ingredient> recipeIngredients = recipe.getIngredients();
            for (int i = 0; i < recipeIngredients.size(); i++) {
                ingredientsResult += ", " + recipeIngredients.get(i).getName();
            }
            // ファイルに書き込む
            print.println(ingredientsResult);
            System.out.println("Recipe added successfully.");
        } catch (IOException e) {
            System.out.println("Failed to add new recipe: " + e.getMessage());
        }
    }

    public ArrayList<Recipe> searchData(String keyword) throws IOException {
        return null;
    }
}

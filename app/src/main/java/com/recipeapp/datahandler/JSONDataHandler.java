package com.recipeapp.datahandler;

import java.io.IOException;
import java.util.ArrayList;

import com.recipeapp.model.Recipe;

public class JSONDataHandler implements DataHandler {
    public String getMode() {
        return "JSON";
    }

    public ArrayList<Recipe> readData() throws IOException {
        return null;
    }

    public void writeData(Recipe recipe) throws IOException {

    }

    public ArrayList<Recipe> searchData(String keyword) throws IOException {
        return null;
    }
}

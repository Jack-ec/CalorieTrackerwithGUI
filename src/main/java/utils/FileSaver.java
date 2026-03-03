package utils;


import objects.Data;
import objects.Food;
import objects.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class FileSaver {

    public static boolean save(File file, Data data) {
        try(FileWriter fw = new FileWriter(file)) {
            fw.write("Foods\n");
            for(Food food: data.getAllFoods()) {
                fw.write(String.format("%s,%s,%s,%s,%s%n", food.getName(), food.getCalories(), food.getProtein(), food.getCarbs(), food.getFat()));
            }
            fw.write("Users\n");
            for(User user: data.getAllUsers()) {
                fw.write(String.format("%s,%s,%s,%s,%s,%s%n", user.getName(), user.getCalories(), user.getProtein(), user.getCarbs(), user.getFat(), user.getConcentration()));
            }
            fw.flush();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}

package utils;

import objects.Data;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class FileLoader {

    public static Data load(File file) {
        Data data = new Data();
        try(Scanner scanner = new Scanner(file)) {
            String line = scanner.nextLine();
            if(!line.equals("Foods")) {
                System.err.println("Could not find Foods Header");
                return null;
            }
            boolean found_users = false;
            while(scanner.hasNextLine()) {
                line = scanner.nextLine();
                if(line.equals("Users")) {
                    found_users = true;
                    break;
                }
                String[] parts = line.split(",");
                if (parts.length != 5) {
                    return null;
                }
                String name = parts[0];
                int calories = Integer.parseInt(parts[1]);
                int protein = Integer.parseInt(parts[2]);
                int carbohydrate = Integer.parseInt(parts[3]);
                int fat = Integer.parseInt(parts[4]);
                data.storeNewFood(name, calories, protein, carbohydrate, fat);
            }
            if (!found_users) {
                System.err.println("Could not find Users Header");
                return null;
            }
            while(scanner.hasNextLine()) {
                line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length != 6) {
                    System.out.println(parts.length);
                    System.err.println("not enough arguments");
                    return null;
                }
                String name = parts[0];
                int calGoals = Integer.parseInt(parts[1]);
                int proGoals = Integer.parseInt(parts[2]);
                int carbGoals = Integer.parseInt(parts[3]);
                int fatGoals = Integer.parseInt(parts[4]);
                String concentration = parts[5];
                data.storeNewUser(name, calGoals, proGoals, carbGoals, fatGoals, concentration);
            }
        } catch (IOException ioe) {
            System.err.println("IO Exception");
            return null;
        }
        return data;
    }
}


package objects;

import java.util.ArrayList;
import java.util.HashMap;

public class Data {
    
    private final ArrayList<User> users;
    
    private final ArrayList<Food> foods;

    private final HashMap<String, User> userIDS;
    
    private static HashMap<String, Food> foodNames;
    
    public Data() {
        foods = new ArrayList<>();
        users = new ArrayList<>();
        userIDS = new HashMap<>();
        foodNames = new HashMap<>();
    }


    /**
     * adds new User object with above parameters to the users ArrayList
     * adds username to the userID HashMap
     * @param userName
     * @param calGoal
     * @param proGoal
     * @param carbGoal
     * @param fatGoal
     * @param concentration
     * @return boolean if successful or not
     */
    public boolean storeNewUser(String userName, int calGoal, int proGoal, int carbGoal, int fatGoal, String concentration) {
        if (!userExists(userName)) {
            User user = new User(userName, calGoal, proGoal, carbGoal, fatGoal, concentration);
            users.add(user);
            userIDS.put(userName, user);
            return true;
        } else {
            return false;
        }
    }


    /**
     * Adds new Food object with parameters to the foods ArrayList
     * adds name to the foodNames HashMap
     * @param name
     * @param cals
     * @param pros
     * @param carbs
     * @param fat
     * @return boolean based on success
     */
    public boolean storeNewFood(String name, int cals, int pros, int carbs, int fat) {
        if (!foodExists(name)) {
            Food food = new Food(name, cals, pros, carbs, fat);
            foods.add(food);
            foodNames.put(name, food);
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return users ArrayList<User>
     */
    public ArrayList<User> getAllUsers() {
        return users;
    }

    /**
     * @return foods ArrayList<Food>
     */
    public ArrayList<Food> getAllFoods() {
        return foods;
    }

    /**
     * @param userName
     * @return User Object from userID HashMap
     */
    public User getUser(String userName) {return userIDS.get(userName);}

    /**
     * @param name
     * @return Food Object from foodNames HashMap
     */
    public Food getFood(String name) {return foodNames.get(name);}

    /**
     * @param userID
     * @return true if userID is a key in userIDs, false otherwise
     */
    public boolean userExists(String userID) {return userIDS.containsKey(userID);}

    /**
     * @param name
     * @return true if name is a key in foodNames, false otherwise
     */
    public boolean foodExists(String name) {return foodNames.containsKey(name);}

    /**
     * @param name
     * removes User Object from users and userIDs
     */
    public void deleteUser(String name) {
        if (userExists(name)) {
            users.remove(getUser(name));
            userIDS.remove(name);
        }
    }

    /**
     * @param name
     * removes Food Object from foods and foodNames
     */
    public void deleteFood(String name) {
        if (foodExists(name)) {
            foods.remove(getFood(name));
            foodNames.remove(name);
        }
    }
}

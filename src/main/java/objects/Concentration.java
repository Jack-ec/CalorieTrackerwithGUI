package objects;

import java.util.ArrayList;

public class Concentration{

    /**
     * Calculates optimal food based off of current food database and user concentration.
     * @param user Allows us to get a specific instance of a user's concentration and goals.
     * @param food Accesses food database.
     * @return Return optimal food and number of servings.
     */
    public int[] concentrationCalculate(User user, ArrayList<Food> food) {

        //initialize variables: access user concentration, untilGoal, and set up arrayLists to add calculations to.
        String concentration = user.getConcentration();
        ArrayList<String> concentrationIndex = new ArrayList<>(); concentrationIndex.add("protein"); concentrationIndex.add("carbohydrates"); concentrationIndex.add("fat");
        int[] untilGoal = user.getUntilGoal();
        ArrayList<Integer> calculation = new ArrayList<>();
        ArrayList<Integer> servings = new ArrayList<>();

        //if concentration goal is passed
        if (untilGoal[concentrationIndex.indexOf(concentration)] <= 0){
            return new int[2];
        }

        //loop through food database and calculate distance from goals and optimal servings for each
        for (int i = 0; i < food.size(); i++) {
            int currentServings;
            int foodCalories = food.get(i).getCalories();
            int foodProtein = food.get(i).getProtein();
            int foodCarbs = food.get(i).getCarbs();
            int foodFat = food.get(i).getFat();
            int excess;
            if (concentration.equals("protein")) {
                if (untilGoal[1] % foodProtein != 0) {
                    currentServings = untilGoal[1] / foodProtein + 1;
                } else {
                    currentServings = untilGoal[1] / foodProtein;
                }
                excess = Math.abs(untilGoal[0] - (foodCalories * currentServings)) + Math.abs(untilGoal[2] - (foodCarbs * currentServings)) + Math.abs(untilGoal[3] - (foodFat * currentServings));
            } else if (concentration.equals("carbohydrates")) {
                if (untilGoal[2] % foodCarbs != 0) {
                    currentServings = untilGoal[2] / foodCarbs + 1;
                } else {
                    currentServings = untilGoal[2] / foodCarbs;
                }
                excess = Math.abs(untilGoal[0] - (foodCalories * currentServings)) + Math.abs(untilGoal[1] - (foodProtein * currentServings)) + Math.abs(untilGoal[3] - (foodFat * currentServings));
            } else {
                if (untilGoal[3] % foodFat != 0) {
                    currentServings = untilGoal[3] / foodFat + 1;
                } else {
                    currentServings = untilGoal[3] / foodFat;
                }
                excess = Math.abs(untilGoal[0] - (foodCalories * currentServings)) + Math.abs(untilGoal[2] - (foodCarbs * currentServings)) + Math.abs(untilGoal[1] - (foodProtein * currentServings));
            }
            calculation.add(excess);
            servings.add(currentServings);
        }

        //find minimum distance value from previous calculations, then set return values accordingly
        int index = 0;
        for (int j = 0; j < calculation.size(); j++) {
            if (calculation.get(j) < calculation.get(index)) {
                index = j;
            }
            else if (calculation.get(j).equals(calculation.get(index))) {
                if (servings.get(j) < servings.get(index)){
                    index = j;
                }
            }
        }
        int[] toReturn = new int[2]; toReturn[0] = index; toReturn[1] = servings.get(index);
        return toReturn;
    }

    public String calcToString(ArrayList<Food> foods, int index, int servings){
        if (servings <= 0){
            return "Goal reached for today!";
        }
        String foodName = foods.get(index).getName();
        return "We recommend eating " + servings + " serving(s) of " + foodName + " to reach your goals.";
    }
}

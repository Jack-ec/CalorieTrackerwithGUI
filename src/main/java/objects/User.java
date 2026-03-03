package objects;

import java.util.Arrays;
import java.util.Objects;

public class User extends Concentration {

    //Jack Chidlaw: initialise goal array, concentration variable
    // index 0: calorie goal, index 1: protein goal, index 2: carbohydrate goal, index 3: fat goal
    //Parth Singh: Create array daily intake to store the daily intake values
    private String name;
    private int[] goals;
    private int[] untilGoal;
    private int[] dailyIntake;
    private boolean goalsAdded;
    private String concentration;

    public User() {
        this.name = "";
        this.goals = new int[4];
        this.untilGoal = new int[4];
        this.dailyIntake = new int[4];
        this.goalsAdded = false;
        this.concentration = "";
    }

    public User(String name, int calGoal, int proGoal, int carbGoal, int fatGoal, String concentration) {
        this.name = name;
        this.goals = new int[4];
        this.goals[0] = calGoal;
        this.goals[1] = proGoal;
        this.goals[2] = carbGoal;
        this.goals[3] = fatGoal;
        this.untilGoal = new int[4];
        this.dailyIntake = new int[4];
        this.goalsAdded = true;
        this.concentration = concentration;
    }

    /**
     * Sets name to the parameter newName.
     * @param newName The string we want to update name to.
     */
    public void setName(String newName) {name = newName;}

    /**
     * Gets name.
     * @return Returns the String name.
     */
    public String getName() {return name;}

    /**
     * Sets the array goals to certain values.
     * @param calories Sets goals[0].
     * @param protein Sets goals[1].
     * @param carbohydrates Sets goals[2].
     * @param fat Sets goals[3].
     */
    public void setGoals(int calories, int protein, int carbohydrates, int fat){
        goals[0] = calories;
        goals[1] = protein;
        goals[2] = carbohydrates;
        goals[3] = fat;
    }

    public int getCalories(){
        return this.goals[0];
    }

    public int getProtein(){
        return this.goals[1];
    }

    public int getCarbs(){
        return this.goals[2];
    }

    public int getFat(){
        return this.goals[3];
    }

    /**
     * Gets goals.
     * @return Returns the array goals.
     */
    public int[] getGoals() {return goals;}

    /**
     * Gets goalsAdded.
     * @return Returns the boolean value goalsAdded.
     */
    public boolean getGoalsAdded(){return goalsAdded;}

    /**
     * Updates the current dailyIntake array to add new values.
     * @param calories Added to dailyIntake[0].
     * @param protein Added to dailyIntake[1].
     * @param carbohydrates Added to dailyIntake[2].
     * @param fat Added to dailyIntake[3].
     */
    public void updateIntake(int calories, int protein, int carbohydrates, int fat){
        dailyIntake[0] = dailyIntake[0] + calories;
        dailyIntake[1] = dailyIntake[1] + protein;
        dailyIntake[2] = dailyIntake[2] + carbohydrates;
        dailyIntake[3] = dailyIntake[3] + fat;
    }

    /**
     * Resets dailyIntake to all 0's.
     */
    public void resetIntake(){
        dailyIntake[0] = 0;
        dailyIntake[1] = 0;
        dailyIntake[2] = 0;
        dailyIntake[3] = 0;
    }

    /**
     * Gets dailyIntake.
     * @return Returns the array dailyIntake.
     */
    public int[] getDailyIntake() {return dailyIntake;}

    /**
     * Calculates how much left until the user reaches their goal, and returns the value.
     * @return Returns the array untilGoal.
     */
    public int[] getUntilGoal(){
        untilGoal[0] = goals[0] - dailyIntake[0];
        untilGoal[1] = goals[1] - dailyIntake[1];
        untilGoal[2] = goals[2] - dailyIntake[2];
        untilGoal[3] = goals[3] - dailyIntake[3];
        return untilGoal;
    }

    /**
     * Sets concentration. Also sets goalsAdded to True.
     * @param newConcentration String that concentration is set to.
     */
    public void setConcentration(String newConcentration) {concentration = newConcentration; goalsAdded = true;}

    /**
     * Gets concentration.
     * @return Returns the String concentration.
     */
    public String getConcentration() {return concentration;}

    /**
     * Returns dailyIntake into string format.
     */
    public String intakeAsString() {return "CURRENT DAILY INTAKE: Calories-" + dailyIntake[0] + ", Protein-" + dailyIntake[1] + "g, Carbohydrates-" + dailyIntake[2] + "g, Fat-" + dailyIntake[3] + "g";}

    /**
     * Returns goals into string format.
     */
    public String goalsAsString() {
        return "GOAL INFORMATION: Calorie Goal-" + goals[0] + ", Protein Goal-" + goals[1] + "g, Carbohydrates Goal-" + goals[2] + "g, Fat Goal-" + goals[3] + "g; CONCENTRATION - " + concentration;
    }
    /**
     * Returns untilGoal into string format.
     */
    public String untilGoalAsString() {
        getUntilGoal();
        return "UNTIL GOAL: Calories Left-" + untilGoal[0] + ", Protein Left-" + untilGoal[1] + "g, Carbohydrates Left-" + untilGoal[2] + "g, Fat Left-" + untilGoal[3] + "g";
    }

    @Override
    public String toString(){
        String toReturn = "USERNAME: " + name + "\n" + intakeAsString();
        if (goalsAdded){
            toReturn = toReturn + "\n" + goalsAsString() + "\n" + untilGoalAsString();
        }
        return toReturn + "\n---";
    }


    //jack chidlaw: sole usage is for unit testing
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return goalsAdded == user.goalsAdded && Objects.equals(name, user.name) && Objects.deepEquals(goals, user.goals) && Objects.deepEquals(untilGoal, user.untilGoal) && Objects.deepEquals(dailyIntake, user.dailyIntake) && Objects.equals(concentration, user.concentration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, Arrays.hashCode(goals), Arrays.hashCode(untilGoal), Arrays.hashCode(dailyIntake), goalsAdded, concentration);
    }

}

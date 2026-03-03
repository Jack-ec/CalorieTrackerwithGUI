package objects;

import java.util.Objects;

public class Food {

    private String name;
    private int calories, protein, carbs, fat;

    //Jack Chidlaw: constructors
    public Food() {
        this.name = "";
        this.calories = 0;
        this.protein = 0;
        this.carbs = 0;
        this.fat = 0;
    }

    public Food(String name) {
        this.name = name.toUpperCase();
    }

    public Food(String name, int calories, int protein, int carbs, int fat) {
        this.name = name.toUpperCase();
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
    }
    //Jack Chidlaw: mutators/accessors
    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    //overrided methods
    @Override
    public String toString() {
        return "Name: " + this.getName() + ", Calories: " + this.getCalories() + ", Protein: " + this.getProtein() + "g, Carbohydrates: " + this.getCarbs() + "g, Fat: " + this.getFat() + "g";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Food other = (Food) obj;
        return java.util.Objects.equals(this.name, other.name);

    }

    @Override
    public int hashCode() {
        return Objects.hash(name, calories, protein, carbs, fat);
    }

    public String compareTo(Food other) {
        int calDifference = this.getCalories() - other.getCalories();
        if (calDifference < 0) {
            calDifference = -calDifference;
        }
        int proDifference = this.getProtein() - other.getProtein();
        if (proDifference < 0) {
            proDifference = -proDifference;
        }
        int carbDifference = this.getCarbs() - other.getCarbs();
        if (carbDifference < 0) {
            carbDifference = -carbDifference;
        }
        int fatDifference = this.getFat() - other.getFat();
        if (fatDifference < 0) {
            fatDifference = -fatDifference;
        }
        return "Difference in Calories: " + Objects.toString(calDifference) + " Difference in Protein: " +
                Objects.toString(proDifference) + " Difference in Carbs: " + Objects.toString(carbDifference) + " Difference in Fat: " + Objects.toString(fatDifference);
    }

}

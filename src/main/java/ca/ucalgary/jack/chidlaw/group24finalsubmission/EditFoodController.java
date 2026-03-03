package ca.ucalgary.jack.chidlaw.group24finalsubmission;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class EditFoodController {

    @FXML
    private TextField name;

    @FXML
    private TextField cals;

    @FXML
    private TextField carb;

    @FXML
    private TextField fat;

    @FXML
    private TextField pro;

    @FXML
    private Label status;

    @FXML
    void onEditButtonClick(ActionEvent event) {
        //Make sure all fields are filled
        if (name.getText().isEmpty() || cals.getText().isEmpty() ||
                pro.getText().isEmpty() || carb.getText().isEmpty() ||
                fat.getText().isEmpty()) {
            status.setTextFill(Color.RED);
            status.setText("Please fill in ALL the fields.");
            return;
        }
        if (!MainController.data.foodExists(name.getText().toUpperCase())) {
            status.setTextFill(Color.RED);
            status.setText("No such food exists.");
            return;
        }
        try {
            String foodName = name.getText().toUpperCase();
            int calories = Integer.parseInt(cals.getText());
            int protein = Integer.parseInt(pro.getText());
            int carbohydrates = Integer.parseInt(carb.getText());
            int fats = Integer.parseInt(fat.getText());

            // Validate nutritional values are positive
            if (calories <= 0 || protein < 0 || carbohydrates < 0 || fats < 0) {
                status.setTextFill(Color.RED);
                status.setText("Nutritional values must be positive numbers.");
                return;
            }
            MainController.data.deleteFood(foodName);
            MainController.data.storeNewFood(foodName, calories, protein, carbohydrates, fats);
            status.setTextFill(Color.GREEN);
            status.setText("Success! Food changed.");

            // Clear fields after successful addition
            name.clear();
            cals.clear();
            pro.clear();
            carb.clear();
            fat.clear();

        } catch (NumberFormatException e) {
            status.setTextFill(Color.RED);
            status.setText("Please enter valid numbers for nutritional values.");
        }
    }
}
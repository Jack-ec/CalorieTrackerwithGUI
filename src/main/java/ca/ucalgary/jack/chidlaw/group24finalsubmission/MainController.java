package ca.ucalgary.jack.chidlaw.group24finalsubmission;

import objects.Data;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import objects.Food;
import objects.User;
import utils.FileLoader;
import utils.FileSaver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainController {

    static Data data = new Data();

    @FXML
    private TextField foodIntake;

    @FXML
    private TextField calIntake;

    @FXML
    private TextField proIntake;

    @FXML
    private TextField carbIntake;

    @FXML
    private TextField fatIntake;

    @FXML
    private TextField userID;

    @FXML
    private Spinner<Integer> servingSpinner;

    @FXML
    private TextField food1;

    @FXML
    private TextField food2;

    @FXML
    private TextField foodName;

    @FXML
    private MenuItem load;

    @FXML
    private MenuItem save;

    @FXML
    private Label statusLabel;

    @FXML
    private Label outputLabel;

    // sets values for serving spinner
    SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99, 0);
    @FXML
    private void initialize(){
        servingSpinner.setValueFactory(valueFactory);
    }

    @FXML
    void load(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Load a file");
        fc.setInitialDirectory(new File("."));
        fc.setInitialFileName("data.csv");
        File file = fc.showOpenDialog(new Stage());
        load(file);
    }

    public void load(File file) {
        statusLabel.setTextFill(Color.BLACK);
        statusLabel.setText("");
        Data data = FileLoader.load(file);
        if (data == null) {
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText(String.format("Failed to load file %s%n", file));
        } else {
            statusLabel.setTextFill(Color.GREEN);
            statusLabel.setText(String.format("Loaded file: %s%n", file));
            MainController.data = data;
        }
    }

    @FXML
    void save(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("save a file");
        fc.setInitialDirectory(new File("."));
        fc.setInitialFileName("data.csv");
        File file = fc.showOpenDialog(new Stage());
        save(file);
    }

    private void save(File file) {
        statusLabel.setTextFill(Color.BLACK);
        statusLabel.setText("");
        FileSaver.save(file, MainController.data);
        if (data == null) {
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText(String.format("Failed to save file %s%n", file));
        } else {
            statusLabel.setTextFill(Color.GREEN);
            statusLabel.setText(String.format("saved file: %s%n", file));
        }
    }

    @FXML
    void onAddtoDIclick(ActionEvent event) {

        // grab UID and check if it's valid
        String uid = userID.getText();
        if (!data.userExists(uid) || uid.isEmpty()) {
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("Please check the username entered");
            return;
        }
        User user = data.getUser(uid);

        // add intake if valid
        try {

            // add food if valid
            String foodName = foodIntake.getText().toUpperCase();
            if (!foodName.isEmpty()){
                if (!data.foodExists(foodName)){
                    statusLabel.setTextFill(Color.RED);
                    statusLabel.setText("Please check the food entered before proceeding");
                    return;
                }
                int servings = servingSpinner.getValue();
                Food food = data.getFood(foodName);
                user.updateIntake(food.getCalories() * servings, food.getProtein() * servings, food.getFat() * servings, food.getCarbs() * servings);
            }

            // add extra intake if valid
            String[] toAdd = new String[4]; toAdd[0] = calIntake.getText(); toAdd[1] = proIntake.getText(); toAdd[2] = carbIntake.getText(); toAdd[3] = fatIntake.getText();
            int[] intake = new int[4];
            for (int index = 0; index < 4; index++){
                if (toAdd[index].isEmpty()){intake[index] = 0; continue;}
                intake[index] = Integer.parseInt(toAdd[index]);
            }
            user.updateIntake(intake[0], intake[1], intake[2], intake[3]);
            statusLabel.setTextFill(Color.GREEN);
            statusLabel.setText("Success!");
            int [] concentrationCalc = user.concentrationCalculate(user, data.getAllFoods());
            if (concentrationCalc != null){
                outputLabel.setText("Intake successfully added: " + user.calcToString(data.getAllFoods(), concentrationCalc[0], concentrationCalc[1]));
                return;
            }
            outputLabel.setText("Intake successfully added!");
        }
        catch (Exception e){
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("Please ensure all macros are integers.");
        }

    }

    @FXML
    void onCompareFoodsButtonClick(ActionEvent event) {

        // grab strings in compare text fields and check if empty/not in database
        String name1 = food1.getText().toUpperCase();
        String name2 = food2.getText().toUpperCase();
        if (name1.isEmpty() || name2.isEmpty()){
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("Please fill in both fields");
            return;
        }
        else if (!data.foodExists(name1) || !data.foodExists(name2)){
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("Please check the name of the foods");
            return;
        }

        // compare foods and output result
        Food compare1 = data.getFood(name1);
        Food compare2 = data.getFood(name2);
        outputLabel.setText(compare1.compareTo(compare2));
    }

    @FXML
    void onViewAllDataButtonClick(ActionEvent event) {

        // grab all data
        ArrayList<Food> foods = MainController.data.getAllFoods();
        ArrayList<User> users = MainController.data.getAllUsers();
        StringBuilder sb = new StringBuilder();

        // view all food data
        if (foods.isEmpty()){
            if (users.isEmpty()){
                statusLabel.setTextFill(Color.RED);
                statusLabel.setText("No information saved.");
                return;
            }
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("No food information saved.");
        }
        else {
            sb.append("(Food Information)\n");
            for (Food food : foods) {
                sb.append(food);
                sb.append("\n");
            }
            sb.append("---\n");
        }

        // view all user data
        if (users.isEmpty()){
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("No user information saved.");
        }
        else {
            sb.append("(User Information)\n");
            for (User user : users) {
                sb.append(user);
                sb.append("\n");
            }

            // output
            outputLabel.setText(sb.toString());
        }
    }

    @FXML
    void onViewDailyIntake(ActionEvent event) {
        String uid = userID.getText();
        if (!data.userExists(uid)){
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("We couldn't find that uid.");
            return;
        }
        User user = data.getUser(uid);
        outputLabel.setText(user.intakeAsString());
    }

    @FXML
    void onResetDailyIntake(ActionEvent event){
        String uid = userID.getText();
        if (!data.userExists(uid)){
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("We couldn't find that uid.");
            return;
        }
        User user = data.getUser(uid);
        user.resetIntake();
        statusLabel.setTextFill(Color.GREEN);
        statusLabel.setText("Daily intake reset!");
    }

    @FXML
    void onViewFoodButtonClick(ActionEvent event) {
        String name = foodName.getText().toUpperCase();
        if (!MainController.data.foodExists(name.toUpperCase()) || name.isEmpty()){
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("Please check the name of the food");
            return;
        }
        Food food = MainController.data.getFood(name.toUpperCase());
        outputLabel.setText(food.toString());
    }

    @FXML
    void onClickViewAllUserInfo(ActionEvent event) {
        ArrayList<User> users = MainController.data.getAllUsers();
        StringBuilder sb = new StringBuilder();
        if (users.isEmpty()){
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("No user information saved.");
            return;
        }
        sb.append("(User Information)\n");
        for (User user : users) {
            sb.append(user);
            sb.append("\n");
        }
        outputLabel.setText(sb.toString());
    }

    @FXML
    void onViewFoodDatabaseClick(ActionEvent event) {
        ArrayList<Food> foods = MainController.data.getAllFoods();
        StringBuilder sb = new StringBuilder();
        for (Food food : foods) {
            sb.append(food);
            sb.append("\n");
        }
        outputLabel.setText(sb.toString());
    }

    @FXML
    void onViewGoalsButtonClick(ActionEvent event) {
        String uid = userID.getText();
        if (!MainController.data.userExists(uid)) {
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("We couldn't find that uid.");
            return;
        }
        User user = MainController.data.getUser(uid);
        outputLabel.setText(user.goalsAsString());
    }


    @FXML
    void userID(ActionEvent event) {

    }

    @FXML
    void addUserGoals(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Add-user.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 300);
        Stage stage = new Stage();
        stage.setTitle("Add User Goals");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void addNewFood(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Add-food.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 300);
        Stage stage = new Stage();
        stage.setTitle("Add New Food");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void editFood(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Edit-food.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 300);
        Stage stage = new Stage();
        stage.setTitle("Edit Food Goals");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void editUser(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Edit-user.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 300);
        Stage stage = new Stage();
        stage.setTitle("Edit User Goals");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onAboutClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Welcome to the Calorie Tracker!\n Creators: Jack Chidlaw (jack.chidlaw@ucalgary.ca), RJ Rinos (rj.rinos@ucalgary.ca), Parth Singh (parth.singh1@ucalgary.ca\n" +
                "You can use this app to track your daily macro nutrient intake\n" +
                "Just enter a userID to save your daily macro nutrient goals and the macro nutrient which is your primary concentration\n" +
                "Add Different kinds off Foods to the database so they can be used in your daily intake tracking aswell as sugestions to help you meet your goals!\n" +
                "save and load this information to the data.csv file.");
        alert.show();
    }

}

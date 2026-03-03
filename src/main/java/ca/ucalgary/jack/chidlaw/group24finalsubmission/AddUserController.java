package ca.ucalgary.jack.chidlaw.group24finalsubmission;

import objects.Data;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class AddUserController {

    @FXML
    private TextField CarbGoal;

    @FXML
    private TextField FatGoal;

    @FXML
    private TextField ProGoal;

    @FXML
    private TextField calGoal;

    ObservableList<String> concentrationChoices = FXCollections.observableArrayList("protein", "carbohydrates", "fat");

    @FXML
    private ChoiceBox concentration;

    @FXML
    private TextField uid;

    @FXML
    private Label status;

    @FXML
    private void initialize(){
        concentration.setItems(concentrationChoices);
    }

    @FXML
    void add(ActionEvent event) {

        // RJ Rinos: if any text is empty, tell them to fill in ALL text fields
        if (uid.getText().isEmpty() || calGoal.getText().isEmpty() || ProGoal.getText().isEmpty() || CarbGoal.getText().isEmpty() || FatGoal.getText().isEmpty() || concentration.getValue() == null){
            status.setTextFill(Color.RED);
            status.setText("Please fill in ALL fields.");
            return;
        }

        Data data = new Data();
        String uid = this.uid.getText();
        if (data.userExists(uid)){
            status.setTextFill(Color.RED);
            status.setText("Sorry, it seems that username is taken. Please try another username.");
            return;
        }
        try {
            int calGoal = Integer.parseInt(this.calGoal.getText());
            int proGoal = Integer.parseInt(this.ProGoal.getText());
            int fatGoal = Integer.parseInt(this.FatGoal.getText());
            int carbGoal = Integer.parseInt(this.CarbGoal.getText());
            String addConcentration = this.concentration.getValue().toString();

            boolean checkStorage = MainController.data.storeNewUser(uid, calGoal, proGoal, fatGoal, carbGoal, addConcentration);
            if (checkStorage) {
                status.setTextFill(Color.GREEN);
                status.setText("Success!");
            }
            else{
                status.setTextFill(Color.RED);
                status.setText("Sorry, it seems something went wrong.");
            }
        }
        catch(Exception e){
            status.setTextFill(Color.RED);
            status.setText("Please enter an integer into your goals.");
        }
    }

}

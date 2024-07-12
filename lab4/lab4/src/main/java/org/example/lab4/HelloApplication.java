package org.example.lab4;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    private ObservableList<String> items;
    private ListView<String> listView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("List Manager");

        items = FXCollections.observableArrayList();
        listView = new ListView<>(items);

        TextField inputField = new TextField();
        inputField.setPromptText("Enter name");

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addItem(inputField));

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteItem());

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(inputField, addButton, deleteButton, listView);

        Scene scene = new Scene(layout, 300, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addItem(TextField inputField) {
        String name = inputField.getText().trim();
        if (validateName(name)) {
            items.add(name);
            inputField.clear();
        } else {
            showAlert("Invalid Name", "Name must start with an uppercase letter, be at least 5 characters long, and not be empty.");
        }
    }

    private boolean validateName(String name) {
        return !name.isEmpty() && name.matches("[A-Z][a-zA-Z ]{4,}");
    }

    private void deleteItem() {
        String selectedItem = listView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            items.remove(selectedItem);
        } else {
            showAlert("No Selection", "Please select an item to delete.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

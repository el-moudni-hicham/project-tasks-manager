package ma.enset.projet.presentation.controllers.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    private Parent fxml;
    @FXML
    private AnchorPane parent;
    @FXML
    private Pane root;


    @FXML
    void home(ActionEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("../../views/user/UserDashboard.fxml"));
            parent.getChildren().removeAll();
            parent.getChildren().setAll(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void tasks(ActionEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("../../views/user/tasks/TasksView.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void signOut(ActionEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("../../views/Login.fxml"));
            parent.getChildren().removeAll();
            parent.getChildren().setAll(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

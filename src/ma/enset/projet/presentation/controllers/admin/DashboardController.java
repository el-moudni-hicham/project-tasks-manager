package ma.enset.projet.presentation.controllers.admin;

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
    private Label username;

    @FXML
    void home(ActionEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("../../views/admin/AdminDashboard.fxml"));
            parent.getChildren().removeAll();
            parent.getChildren().setAll(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void users(ActionEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("../../views/admin/users/UsersView.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void projects(ActionEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("../../views/admin/projects/ProjectsView.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void tasks(ActionEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("../../views/admin/tasks/TasksView.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void materials(ActionEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("../../views/admin/materials/MaterialsView.fxml"));
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

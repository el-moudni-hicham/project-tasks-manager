package ma.enset.projet.presentation.controllers.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import ma.enset.projet.dao.ResourceHumaineDaoImpl;
import ma.enset.projet.services.RhService;
import ma.enset.projet.services.RhServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    private Parent fxml;
    @FXML
    private AnchorPane parent;
    @FXML
    private Pane root;
    @FXML
    private Label rhNumber;
    @FXML
    private PieChart chart;



    @FXML
    private Label username;
    RhService rhs;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rhs = new RhServiceImpl(new ResourceHumaineDaoImpl());
        rhNumber.setText(String.valueOf(rhs.countRh()));

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Human Resources", rhs.countRh()),
                        new PieChart.Data("Material Resources", 25),
                        new PieChart.Data("Projects", 10)
                        );
        chart.setData(pieChartData);
        chart.setTitle("Application Statistics");
        chart.setVisible(true);

    }
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




}

package ma.enset.projet.presentation.controllers.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ma.enset.projet.dao.MaterielleDaoImpl;
import ma.enset.projet.dao.ProjetDaoImpl;
import ma.enset.projet.dao.ResourceHumaineDao;
import ma.enset.projet.dao.ResourceHumaineDaoImpl;
import ma.enset.projet.presentation.controllers.admin.profile.ProfieController;
import ma.enset.projet.services.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    private Label matNumber;
    @FXML
    private Label prjNumber;
    @FXML
    private PieChart chart;
    @FXML
    private Label nameUser;
    @FXML
    private Button username;


    private int adminId;

    private String UserRole;
    Stage stage;

    public String getUserRole() {
        return UserRole;
    }

    public void setUserRole(String userRole) {
        UserRole = userRole;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    ResourceHumaineDao rhd = new ResourceHumaineDaoImpl();
    public void getAdminName(int id){
            username.setText(rhd.getName(id));
    }

    RhService rhs;
    MaterielleService ms;
    ProjetService ps;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rhs = new RhServiceImpl(new ResourceHumaineDaoImpl());
        ms = new MaterielleServiceImpl(new MaterielleDaoImpl());
        ps = new ProjetServiceImpl(new ProjetDaoImpl());
        rhNumber.setText(String.valueOf(rhs.countRh()));
        matNumber.setText(String.valueOf(ms.countMat()));
        prjNumber.setText(String.valueOf(ps.countProjects()));
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Human Resources", rhs.countRh()),
                        new PieChart.Data("Material Resources", ms.countMat()),
                        new PieChart.Data("Projects", ps.countProjects())
                        );
        chart.setData(pieChartData);
        chart.setTitle("Application Statistics");
        chart.setVisible(true);

    }
    @FXML
    void home(ActionEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("../../views/admin/HomeView.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
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
    void profile(ActionEvent event) throws SQLException {
            FXMLLoader Loader = new FXMLLoader();

            Loader.setLocation(getClass().getResource("../../views/admin/profile/profileView.fxml"));

            try {
                Loader.load();
            } catch (Exception e) {
                e.printStackTrace();
            }

            ProfieController adminProfile = Loader.getController();
            adminProfile.setAdminId(getAdminId());
            adminProfile.setAdminInfo(getAdminId());
            adminProfile.setUserRole(getUserRole());

            Parent parent = Loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        /*
            Parent p = Loader.getRoot();
            stage = (Stage) root.getScene().getWindow();
            Scene scene = new Scene(p, Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight());
            stage.setScene(scene);
            stage.show();

         */

    }

    @FXML
    void signOut(ActionEvent event) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you sure you would like to log out ?");
            Optional<ButtonType> optional = alert.showAndWait();
            if (optional.get() == ButtonType.OK) {
                fxml = FXMLLoader.load(getClass().getResource("../../views/Login.fxml"));
                parent.getChildren().removeAll();
                parent.getChildren().setAll(fxml);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




}

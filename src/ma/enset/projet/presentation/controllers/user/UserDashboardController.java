package ma.enset.projet.presentation.controllers.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ma.enset.projet.dao.TacheDaoImpl;
import ma.enset.projet.presentation.controllers.admin.profile.ProfieController;
import ma.enset.projet.presentation.controllers.user.tasks.TasksController;
import ma.enset.projet.services.TacheService;
import ma.enset.projet.services.TacheServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class UserDashboardController implements Initializable {

    private Parent fxml;
    @FXML
    private AnchorPane parent;
    @FXML
    private Label NbTasks;

    TacheService ts = new TacheServiceImpl(new TacheDaoImpl());
    public void setNbTasks(int id) {
        NbTasks.setText(String.valueOf(ts.countTasks(id)));
    }

    @FXML
    private Pane root;

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


    @FXML
    void tasks(ActionEvent event) {
        /*try {
            fxml = FXMLLoader.load(getClass().getResource("../../views/user/tasks/TasksView.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

         */

        FXMLLoader Loader = new FXMLLoader();

        Loader.setLocation(getClass().getResource("../../views/user/tasks/TasksView.fxml"));

        try {
            Loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }


        TasksController tasksController = Loader.getController();
        tasksController.loadData(getAdminId());


        Parent parent = Loader.getRoot();
        //root.getChildren().removeAll();
        //root.getChildren().setAll(parent);
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }

    @FXML
    void profile(ActionEvent event) throws SQLException {
        FXMLLoader Loader = new FXMLLoader();

        Loader.setLocation(getClass().getResource("../../views/user/profile/profileView.fxml"));

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
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

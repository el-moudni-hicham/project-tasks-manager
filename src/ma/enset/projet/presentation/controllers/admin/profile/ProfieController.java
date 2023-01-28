package ma.enset.projet.presentation.controllers.admin.profile;


import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ma.enset.projet.dao.ResourceHumaineDaoImpl;
import ma.enset.projet.dao.entites.ResourceHumaine;
import ma.enset.projet.presentation.controllers.admin.DashboardController;
import ma.enset.projet.services.RhService;
import ma.enset.projet.services.RhServiceImpl;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ProfieController implements Initializable {

    private int adminId;
    private String UserRole;

    public String getUserRole(){ return UserRole; }

    public void setUserRole(String userRole){ this.UserRole=userRole; }

    public int getAdminId(){ return adminId; }

    public void setAdminId(int adminid){ this.adminId=adminid; }

    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField txtNom;

    @FXML
    private JFXTextField txtPrenom;

    @FXML
    private JFXTextField txtDate;

    @FXML
    private JFXTextField txtFonction;



    @FXML
    private JFXTextField txtTelephone;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    private Button btnSave;

    @FXML
    private Button backHome;
    Stage stage;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnSave.setVisible(false);
        btnSave.setDisable(true);

        txtNom.setEditable(false);
        txtPrenom.setEditable(false);
        txtDate.setEditable(false);
        txtFonction.setEditable(false);
        txtTelephone.setEditable(false);
        txtEmail.setEditable(false);

        txtUsername.setEditable(false);
        txtPassword.setEditable(false);
    }

    RhService rhs = new RhServiceImpl(new ResourceHumaineDaoImpl());
    public void setAdminInfo(int id) throws SQLException {
        ResourceHumaine rh = rhs.getRhById(id);
        txtNom.setText(rh.getNom());
        txtPrenom.setText(rh.getPrenom());
        txtDate.setText(rh.getDate_naissance());
        txtFonction.setText(rh.getFonction());
        txtTelephone.setText(rh.getTelephone());
        txtEmail.setText(rh.getEmail());

        txtUsername.setText(rh.getUsername());
        txtPassword.setText(rh.getPassword());

    }

    /*
    @FXML
    void backHome(ActionEvent event) {

        if(event.getSource() == backHome){
            FXMLLoader Loader = new FXMLLoader();

            Loader.setLocation(getClass().getResource("../../../views/admin/AdminDashboard.fxml"));
            try{
                Loader.load();
            } catch (Exception e){
                e.printStackTrace();
            }

            DashboardController dashboardController = Loader.getController();
            dashboardController.setAdminId(adminId);
            dashboardController.setUserRole(getUserRole());
            dashboardController.getAdminName(adminId);

            Parent p = Loader.getRoot();
            stage = (Stage) backHome.getScene().getWindow();
            Scene scene = new Scene(p);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        }

    }

     */

    @FXML
    void editProfile(ActionEvent event) {
        btnSave.setVisible(true);
        btnSave.setDisable(false);

        txtUsername.setEditable(true);
        txtPassword.setEditable(true);
    }

    @FXML
    void saveProfile(ActionEvent event) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();


        if(username.trim().isEmpty() || password.trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please fill up the form correctly !");
            alert.show();
        }
        rhs.updateLofinInfo(adminId,username,password);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Login Info Updated Successfully !");
        alert.show();
    }
}


package ma.enset.projet.presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ma.enset.projet.dao.ResourceHumaineDaoImpl;
import ma.enset.projet.dao.entites.ResourceHumaine;
import ma.enset.projet.presentation.controllers.admin.DashboardController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {

    @FXML
    private AnchorPane content;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    int idAdmin;
    String role;
    Stage stage;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void handel_login(ActionEvent event) throws IOException {
        ResourceHumaineDaoImpl rhd = new ResourceHumaineDaoImpl();
        FXMLLoader Loader = new FXMLLoader();

        if(txtUsername.getText().isEmpty() || txtPassword.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Veuillez remplir tout les champs !");
            alert.show();
        }else{
            String username = txtUsername.getText();
            String password = txtPassword.getText();
            ResourceHumaine rh = rhd.checkRH(username,password);
            if(rh != null){
                role = rh.getRole();
                if(role.equals("ADMIN")){
                    //Parent menu = FXMLLoader.load(getClass().getResource("../views/admin/AdminDashboard.fxml"));
                    //content.getChildren().removeAll();
                    //content.getChildren().setAll(menu);

                    //load up OTHER FXML document
                    Loader.setLocation(getClass().getResource("../views/admin/AdminDashboard.fxml"));
                    idAdmin = rh.getId();
                    try{
                        Loader.load();
                    } catch (Exception e){
                        e.printStackTrace();
                    }

                    DashboardController dashboardController = Loader.getController();
                    dashboardController.setAdminId(idAdmin);
                    dashboardController.setUserRole(role);
                    dashboardController.getAdminName(idAdmin);

                    Parent p = Loader.getRoot();
                    stage = (Stage) content.getScene().getWindow();
                    Scene scene = new Scene(p, Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight());
                    stage.setScene(scene);
                    stage.centerOnScreen();
                    stage.show();


                }else{
                    Parent menu = FXMLLoader.load(getClass().getResource("../views/user/UserDashboard.fxml"));
                    content.getChildren().removeAll();
                    content.getChildren().setAll(menu);
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Username / password is wrong !");
                alert.show();
            }

        }

        
    }
    
}

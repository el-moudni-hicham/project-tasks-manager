
package ma.enset.projet.presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import ma.enset.projet.dao.UserDaoImpl;
import ma.enset.projet.dao.entites.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {

    @FXML
    private AnchorPane parent;
    @FXML
    private Pane content;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;




    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void handel_login(ActionEvent event) throws IOException {
        UserDaoImpl userDao = new UserDaoImpl();
        if(txtUsername.getText().isEmpty() || txtPassword.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Veuillez remplir tout les champs !");
            alert.show();
        }else{
            String username = txtUsername.getText();
            String password = txtPassword.getText();
            User user = userDao.checkUser(username, password);
            if(user != null){
                if(user.getRole().equals("ADMIN")){
                    Parent menu = FXMLLoader.load(getClass().getResource("../views/admin/AdminDashboard.fxml"));
                    content.getChildren().removeAll();
                    content.getChildren().setAll(menu);
                }else{
                    Parent menu = FXMLLoader.load(getClass().getResource("../views/user/UserDashboard.fxml"));
                    content.getChildren().removeAll();
                    content.getChildren().setAll(menu);
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Check Your Informations ! !");
                alert.show();
            }

        }

        
    }
    
}


package ma.enset.projet.presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {

    @FXML
    private AnchorPane parent;
    @FXML
    private Pane content;
    
    
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handel_login(ActionEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("/loginapplication/views/Menu.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(menu);
        
    }
    
}

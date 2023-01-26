package ma.enset.projet.presentation.controllers.admin.materials;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MaterialsController implements Initializable {
    private Parent fxml;
    @FXML
    private AnchorPane root;
    @FXML
    void addMaterial(ActionEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("../../../views/admin/materials/AddView.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

package ma.enset.projet.presentation.controllers.admin.materials;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import ma.enset.projet.dao.MaterielleDaoImpl;
import ma.enset.projet.dao.ResourceHumaineDao;
import ma.enset.projet.dao.ResourceHumaineDaoImpl;
import ma.enset.projet.dao.entites.Materiele;
import ma.enset.projet.dao.entites.ResourceHumaine;
import ma.enset.projet.services.MaterielleService;
import ma.enset.projet.services.MaterielleServiceImpl;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

public class AddController implements Initializable {

    private Parent fxml;
    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField txtNom;

    @FXML
    private JFXTextField txtCar;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void addMat() {
        if(txtNom.getText().isEmpty() || txtCar.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Veuillez remplir tout les champs !");
            alert.show();
        }else{
            String nom = txtNom.getText();
            String car = txtCar.getText();

            Materiele mat = new Materiele();
            mat.setNom(nom);
            mat.setCaracteristique(car);

            MaterielleService ms = new MaterielleServiceImpl(new MaterielleDaoImpl());
            ms.addMat(mat);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("User a ete ajouter avec succes");
            alert.show();

            try {
                fxml = FXMLLoader.load(getClass().getResource("../../../views/admin/materials/MaterialsView.fxml"));
                root.getChildren().removeAll();
                root.getChildren().setAll(fxml);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}

package ma.enset.projet.presentation.controllers.admin.materials;


import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import ma.enset.projet.dao.MaterielleDaoImpl;
import ma.enset.projet.dao.ResourceHumaineDaoImpl;
import ma.enset.projet.dao.entites.Materiele;
import ma.enset.projet.services.MaterielleService;
import ma.enset.projet.services.MaterielleServiceImpl;
import ma.enset.projet.services.RhService;
import ma.enset.projet.services.RhServiceImpl;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EditController implements Initializable {

    private Parent fxml;
    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField txtNom;

    @FXML
    private JFXTextField txtCar;

    ObservableList<String> observableList = FXCollections.observableArrayList();
    List<String> list = new ArrayList<>();
    private boolean update;
    int matId;
    MaterielleService ms;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    void setTextField(int id, String nom,String caractiristique) {
        matId = id;
        txtNom.setText(nom);
        txtCar.setText(caractiristique);
    }

    void setUpdate(boolean b) {
        this.update = b;

    }
    @FXML
    public void editMat() {
            String nom = txtNom.getText();
            String car = txtCar.getText();

            ms = new MaterielleServiceImpl(new MaterielleDaoImpl());
            Materiele materiele = new Materiele(matId,nom,car);
            ms.updateMat(materiele);


            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Material Updated Successfully ");
            alert.show();
    }


}


package ma.enset.projet.presentation.controllers.admin.users;


import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import ma.enset.projet.dao.ResourceHumaineDao;
import ma.enset.projet.dao.ResourceHumaineDaoImpl;
import ma.enset.projet.dao.entites.ResourceHumaine;
import ma.enset.projet.services.RhService;
import ma.enset.projet.services.RhServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class EditController implements Initializable {

    private Parent fxml;
    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField txtNom;

    @FXML
    private JFXTextField txtPrenom;

    @FXML
    private JFXDatePicker txtDate;

    @FXML
    private JFXTextField txtFonction;

    @FXML
    private JFXComboBox<String> txtRole;

    @FXML
    private JFXTextField txtTelephone;

    @FXML
    private JFXTextField txtEmail;
    ObservableList<String> observableList = FXCollections.observableArrayList();
    List<String> list = new ArrayList<>();
    private boolean update;
    int rhId;
    RhService rhs;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    void setTextField(int id, String nom,String prenom,String fonction,String email,String telephone) {
        rhId = id;
        txtNom.setText(nom);
        txtPrenom.setText(prenom);
        txtFonction.setText(fonction);
        txtEmail.setText(email);
        txtTelephone.setText(telephone);
    }

    void setUpdate(boolean b) {
        this.update = b;

    }
    @FXML
    public void editUser() {
            String nom = txtNom.getText();
            String prenom = txtPrenom.getText();
            String fonction = txtFonction.getText();
            String telephone = txtTelephone.getText();
            String email = txtEmail.getText();


            rhs = new RhServiceImpl(new ResourceHumaineDaoImpl());
            rhs.updateRh(rhId,nom,prenom,fonction,email,telephone);


            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("User Updated Succes");
            alert.show();
    }


}


package ma.enset.projet.presentation.controllers.admin.users;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import ma.enset.projet.dao.ResourceHumaineDao;
import ma.enset.projet.dao.ResourceHumaineDaoImpl;
import ma.enset.projet.dao.entites.ResourceHumaine;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class AddController implements Initializable {

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
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list.add("ADMIN");
        list.add("USER");
        observableList.addAll(list);
        txtRole.setItems(observableList);
    }

    @FXML
    public void addUser() {
        if(txtNom.getText().isEmpty() || txtPrenom.getText().isEmpty() || txtFonction.getText().isEmpty()
                || txtTelephone.getText().isEmpty() || txtEmail.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Veuillez remplir tout les champs !");
            alert.show();
        }else{
            String nom = txtNom.getText();
            String prenom = txtPrenom.getText();
            String fonction = txtFonction.getText();
            String telephone = txtTelephone.getText();
            String email = txtEmail.getText();
            LocalDate date = txtDate.getValue();
            int year = date.getYear();
            int month = date.getMonthValue();
            int day = date.getDayOfMonth();
            Date d_naiss = new Date(year, month, day);

            String role = txtRole.getValue();

            ResourceHumaine rh = new ResourceHumaine();
            rh.setNom(nom);
            rh.setPrenom(prenom);
            rh.setFonction(fonction);
            rh.setDate_naissance(String.valueOf(d_naiss));
            rh.setTelephone(telephone);
            rh.setEmail(email);
            rh.setUsername(nom+" "+prenom);
            rh.setPassword(nom+year);
            rh.setRole(role);

            ResourceHumaineDao rhd = new ResourceHumaineDaoImpl();
            rhd.save(rh);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("User a ete ajouter avec succes");
            alert.show();
        }
    }

}

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

public class AddController implements Initializable {

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
            alert.setContentText("Please complete all fields !");
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
            rh.setDate_naissance(d_naiss.getYear()+"-"+d_naiss.getMonth()+"-"+d_naiss.getDate());
            rh.setTelephone(telephone);
            rh.setEmail(email);
            rh.setUsername(nom+" "+prenom);
            rh.setPassword(nom+year);
            rh.setRole(role);

            RhService rhs = new RhServiceImpl(new ResourceHumaineDaoImpl());
            rhs.addRh(rh);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("User added successfully ");
            alert.show();

            try {
                fxml = FXMLLoader.load(getClass().getResource("../../../views/admin/users/UsersView.fxml"));
                root.getChildren().removeAll();
                root.getChildren().setAll(fxml);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

}

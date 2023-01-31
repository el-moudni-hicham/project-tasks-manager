package ma.enset.projet.presentation.controllers.admin.projects;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import ma.enset.projet.dao.ProjetDaoImpl;
import ma.enset.projet.dao.ResourceHumaineDaoImpl;
import ma.enset.projet.dao.entites.Projet;
import ma.enset.projet.dao.entites.ResourceHumaine;
import ma.enset.projet.services.ProjetService;
import ma.enset.projet.services.ProjetServiceImpl;
import ma.enset.projet.services.RhService;
import ma.enset.projet.services.RhServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
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
    private JFXComboBox<ResourceHumaine> txtRespo;

    @FXML
    private JFXDatePicker dateDebut;

    @FXML
    private JFXDatePicker dateFin;

    ProjetService ps = new ProjetServiceImpl(new ProjetDaoImpl());
    @FXML
    void addProject(ActionEvent event) {
        if(txtNom.getText().isEmpty() || txtRespo.getValue().equals(null) || dateDebut.getValue().equals(null) || dateFin.getValue().equals(null)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please complete all fields !");
            alert.show();
        }else{
            String nom = txtNom.getText();
            ResourceHumaine responsable = txtRespo.getValue();

            LocalDate start = dateDebut.getValue();
            int year = start.getYear();
            int month = start.getMonthValue();
            int day = start.getDayOfMonth();
            Date d_start = new Date(year, month, day);

            LocalDate fin = dateFin.getValue();
            int year1 = fin.getYear();
            int month1 = fin.getMonthValue();
            int day1 = fin.getDayOfMonth();
            Date d_fin = new Date(year1, month1, day1);

            LocalDate startDate = start;
            LocalDate endDate = fin;
            Period period = Period.between(startDate, endDate);
            int estimated_time = period.getDays();

            Projet projet = new Projet();
            projet.setNom(nom);
            projet.setResponsable(responsable);
            projet.setDat_debut(d_start);
            projet.setDat_fin(d_fin);
            projet.setEstimated_time(estimated_time);

            ps.addProjet(projet);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Project added successfully");
            alert.show();

            try {
                fxml = FXMLLoader.load(getClass().getResource("../../../views/admin/projects/ProjectsView.fxml"));
                root.getChildren().removeAll();
                root.getChildren().setAll(fxml);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    ObservableList<ResourceHumaine> resourceHumaineObservableList = FXCollections.observableArrayList();
    RhService rhs;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rhs = new RhServiceImpl(new ResourceHumaineDaoImpl());
        List<ResourceHumaine> resourceHumaineList = rhs.getAllRhs();
        resourceHumaineObservableList.addAll(resourceHumaineList);
        txtRespo.setItems(resourceHumaineObservableList);
    }
}

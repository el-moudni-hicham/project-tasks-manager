package ma.enset.projet.presentation.controllers.admin.tasks;

import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import ma.enset.projet.dao.MaterielleDaoImpl;
import ma.enset.projet.dao.ProjetDaoImpl;
import ma.enset.projet.dao.ResourceHumaineDaoImpl;
import ma.enset.projet.dao.TacheDaoImpl;
import ma.enset.projet.dao.entites.Materiele;
import ma.enset.projet.dao.entites.Projet;
import ma.enset.projet.dao.entites.ResourceHumaine;
import ma.enset.projet.dao.entites.Tache;
import ma.enset.projet.services.*;
import org.controlsfx.control.CheckComboBox;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class AddMaterialController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private CheckComboBox<Materiele> txtMat;





    ObservableList<Materiele> materielleObservableList = FXCollections.observableArrayList();
    RhService rhs;
    MaterielleService ms;
    ProjetService ps;
    TacheService ts;

    int taskId;

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    List<Materiele> materieleList = new ArrayList<>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ps = new ProjetServiceImpl(new ProjetDaoImpl());
        rhs = new RhServiceImpl(new ResourceHumaineDaoImpl());
        ts = new TacheServiceImpl(new TacheDaoImpl());


        ms = new MaterielleServiceImpl(new MaterielleDaoImpl());
        List<Materiele> materieleList = ms.getAllMats();
        materielleObservableList.addAll(materieleList);
        txtMat.getItems().setAll(materielleObservableList);

        //Projet projet = ps.getProjetById(Integer.parseInt(txtProjet.getText()));

        txtMat.getCheckModel().getCheckedItems().addListener(new ListChangeListener<Materiele>() {
            public void onChanged(ListChangeListener.Change<? extends Materiele> c) {
                //System.out.println(txtMat.getCheckModel().getCheckedItems());


            }
        });
    }

    ObservableList<Materiele> materieleObservableList = FXCollections.observableArrayList();
    List<Materiele> list = new ArrayList<>();


    @FXML
    public void addMat(ActionEvent event) {
        materieleObservableList = txtMat.getCheckModel().getCheckedItems();
        list = materieleObservableList;
        for (Materiele materiele:list) {
            ts.addMatToTask(getTaskId(),materiele.getId());
        }

    }

}

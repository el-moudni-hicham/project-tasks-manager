package ma.enset.projet.presentation.controllers.user.tasks;


import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import ma.enset.projet.dao.ResourceHumaineDaoImpl;
import ma.enset.projet.dao.TacheDaoImpl;
import ma.enset.projet.services.RhService;
import ma.enset.projet.services.RhServiceImpl;
import ma.enset.projet.services.TacheService;
import ma.enset.projet.services.TacheServiceImpl;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EditController implements Initializable {

    private Parent fxml;
    @FXML
    private AnchorPane root;


    @FXML
    private JFXTextField txtSatut;

    @FXML
    private JFXToggleButton toggleBtn;


    int rhId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        toggleBtn.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(toggleBtn.isSelected() == true){
                    toggleBtn.setText("COMPLETED");
                }else{
                    toggleBtn.setText("NOT COMPLETED");
                }
            }
        });
    }

    void setTextField(int id, Boolean statut) {
        rhId = id;
        if(statut.booleanValue()==false) {
            txtSatut.setText("NOT COMLETED");
        }else{
            txtSatut.setText("COMPLETED");
        }
        //toggleButton.setDisableAnimation(statut);
    }

    TacheService ts;
    @FXML
    public void editStatut(ActionEvent event) {
            Boolean statut = toggleBtn.isSelected();
            ts = new TacheServiceImpl(new TacheDaoImpl());
            ts.updateTache(rhId,statut);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Task Statu Updated Succes");
            alert.show();
    }


}


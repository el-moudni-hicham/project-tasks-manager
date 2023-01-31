package ma.enset.projet.presentation.controllers.admin.tasks;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import ma.enset.projet.dao.MaterielleDaoImpl;
import ma.enset.projet.dao.ProjetDaoImpl;
import ma.enset.projet.dao.ResourceHumaineDaoImpl;
import ma.enset.projet.dao.TacheDaoImpl;
import ma.enset.projet.dao.entites.Materiele;
import ma.enset.projet.services.*;
import org.controlsfx.control.CheckComboBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MaterialController implements Initializable {

    private Parent fxml;
    @FXML
    private AnchorPane root;


    @FXML
    private TableColumn<Materiele, Integer> colId;

    @FXML
    private TableColumn<Materiele, String> colNom;

    @FXML
    private TableColumn<Materiele, String> colCar;



    @FXML
    private TableView<Materiele> matsTableView;


    ObservableList<Materiele> materielleObservableList = FXCollections.observableArrayList();
    MaterielleService ms;
    TacheService ts;

    int taskId;

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        ts = new TacheServiceImpl(new TacheDaoImpl());
        ms = new MaterielleServiceImpl(new MaterielleDaoImpl());
        integerList = ts.TaskMats(taskId);
        materieleList = new ArrayList<>();
        for (Integer i : integerList) {
            ms.getMatById(i);
            materieleList.add(ms.getMatById(i));
        }
        materielleObservableList.addAll(materieleList);
        matsTableView.setItems(materielleObservableList);
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colCar.setCellValueFactory(new PropertyValueFactory<>("caracteristique"));
    }

    List<Materiele> materieleList;
    List<Integer> integerList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }






}

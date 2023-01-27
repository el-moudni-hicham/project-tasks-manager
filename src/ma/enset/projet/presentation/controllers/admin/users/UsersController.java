package ma.enset.projet.presentation.controllers.admin.users;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import ma.enset.projet.dao.ResourceHumaineDao;
import ma.enset.projet.dao.ResourceHumaineDaoImpl;
import ma.enset.projet.dao.entites.ResourceHumaine;
import ma.enset.projet.services.RhService;
import ma.enset.projet.services.RhServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UsersController implements Initializable {
    private Parent fxml;
    @FXML
    private AnchorPane root;


    @FXML
    private TableColumn<ResourceHumaine, Integer> colId;

    @FXML
    private TableColumn<ResourceHumaine, String> colNom;

    @FXML
    private TableColumn<ResourceHumaine, String> colPrenom;

    @FXML
    private TableColumn<ResourceHumaine, String> colDateN;

    @FXML
    private TableColumn<ResourceHumaine, String> colFonction;

    @FXML
    private TableColumn<ResourceHumaine, Boolean> colDisponible;

    @FXML
    private TableColumn<ResourceHumaine, String> colDateI;

    @FXML
    private TableColumn<ResourceHumaine, String> colRole;

    @FXML
    private TableColumn<ResourceHumaine, String> colTelephone;

    @FXML
    private TableColumn<ResourceHumaine, String> colEmail;

    @FXML
    private TableColumn<ResourceHumaine, String> colActions;

    @FXML
    private TableView<ResourceHumaine> usersTableView;

    @FXML
    private ObservableList<ResourceHumaine> observableList = FXCollections.observableArrayList();

    RhService rhs;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rhs = new RhServiceImpl(new ResourceHumaineDaoImpl());
        usersTableView.setItems(observableList);
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colFonction.setCellValueFactory(new PropertyValueFactory<>("fonction"));
        colDateN.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
        colTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colDateI.setCellValueFactory(new PropertyValueFactory<>("date_insc"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colDisponible.setCellValueFactory(new PropertyValueFactory<>("disponible"));

        loadUsers();
    }

    private void loadUsers(){
        observableList.clear();
        observableList.addAll(rhs.getAllRhs());
    }


    @FXML
    void addUser(ActionEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("../../../views/admin/users/AddView.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

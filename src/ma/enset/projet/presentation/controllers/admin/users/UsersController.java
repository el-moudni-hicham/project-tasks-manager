package ma.enset.projet.presentation.controllers.admin.users;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import ma.enset.projet.dao.ResourceHumaineDao;
import ma.enset.projet.dao.ResourceHumaineDaoImpl;
import ma.enset.projet.dao.entites.ResourceHumaine;
import ma.enset.projet.services.RhService;
import ma.enset.projet.services.RhServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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

    //@FXML
    //private TableColumn<ResourceHumaine, String> colDateN;

    @FXML
    private TableColumn<ResourceHumaine, String> colFonction;

    @FXML
    private TableColumn<ResourceHumaine, Boolean> colDisponible;

    //@FXML
    //private TableColumn<ResourceHumaine, String> colDateI;

    //@FXML
    //private TableColumn<ResourceHumaine, String> colRole;

    @FXML
    private TableColumn<ResourceHumaine, String> colTelephone;

    @FXML
    private TableColumn<ResourceHumaine, String> colEmail;

    @FXML
    private TableColumn<ResourceHumaine, String> colActions;

    @FXML
    private TextField txtSearch;

    @FXML
    private TableView<ResourceHumaine> usersTableView;

    @FXML
    private ObservableList<ResourceHumaine> observableList = FXCollections.observableArrayList();

    RhService rhs;
    ResourceHumaine rh;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rhs = new RhServiceImpl(new ResourceHumaineDaoImpl());
        usersTableView.setItems(observableList);
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colFonction.setCellValueFactory(new PropertyValueFactory<>("fonction"));
        //colDateN.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
        colTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        //colDateI.setCellValueFactory(new PropertyValueFactory<>("date_insc"));
        //colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colDisponible.setCellValueFactory(new PropertyValueFactory<>("disponible"));



        //add cell of button edit
        Callback<TableColumn<ResourceHumaine, String>, TableCell<ResourceHumaine, String>> cellFoctory = (TableColumn<ResourceHumaine, String> param) -> {
            // make cell containing buttons
            final TableCell<ResourceHumaine, String> cell = new TableCell<ResourceHumaine, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        Image image = new Image("ma/enset/projet/presentation/images/icons8-user-settings-28.png");
                        ImageView editIcon = new ImageView();
                        editIcon.setImage(image);

                        Image image1 = new Image("ma/enset/projet/presentation/images/icons8-delete-user-male-28.png");
                        ImageView deleteIcon = new ImageView();
                        deleteIcon.setImage(image1);

                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            MultipleSelectionModel<ResourceHumaine> rh = usersTableView.getSelectionModel();
                            if(rh != null){
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setContentText("Veuillez vous supprimer ce User ?");
                                Optional<ButtonType> optional = alert.showAndWait();
                                if (optional.get() == ButtonType.OK) {
                                    rhs.deleteRh(rh.getSelectedItem());
                                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                                    alert1.setContentText("User a ete supprimer avec succes");
                                    alert1.show();
                                    loadUsers();
                                }
                            } else{
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setContentText("Veuillez selectionner un element ! !");
                                alert.show();
                            }
                        });

                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            rh = usersTableView.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("../../../views/admin/users/editView.fxml"));

                            try {
                                loader.load();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }


                            EditController editController = loader.getController();
                            editController.setUpdate(true);
                            editController.setTextField(rh.getId(),rh.getNom(),rh.getPrenom(),rh.getFonction(),rh.getEmail(),rh.getTelephone());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                            //loadUsers();
                        });



                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));

                        setGraphic(managebtn);

                        setText(null);


                    }
                }

            };

            return cell;
        };
        colActions.setCellFactory(cellFoctory);


        txtSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                observableList.clear();
                observableList.addAll(rhs.searchRhByQuery(newValue));
            }
        });


        loadUsers();
    }

    public void loadUsers(){
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

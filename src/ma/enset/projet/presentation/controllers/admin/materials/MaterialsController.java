package ma.enset.projet.presentation.controllers.admin.materials;

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
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import ma.enset.projet.dao.MaterielleDaoImpl;
import ma.enset.projet.dao.entites.Materiele;
import ma.enset.projet.services.MaterielleService;
import ma.enset.projet.services.MaterielleServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MaterialsController implements Initializable {
    private Parent fxml;
    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtSearch;

    @FXML
    private TableColumn<Materiele, Integer> colId;

    @FXML
    private TableColumn<Materiele, String> colNom;

    @FXML
    private TableColumn<Materiele, String> colCar;

    @FXML
    private TableColumn<Materiele, String> colActions;

    @FXML
    private TableView<Materiele> matsTableView;

    @FXML
    private ObservableList<Materiele> observableList = FXCollections.observableArrayList();

    MaterielleService ms;
    Materiele mat;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ms = new MaterielleServiceImpl(new MaterielleDaoImpl());
        matsTableView.setItems(observableList);
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colCar.setCellValueFactory(new PropertyValueFactory<>("caracteristique"));

        //add cell of button edit
        Callback<TableColumn<Materiele, String>, TableCell<Materiele, String>> cellFoctory = (TableColumn<Materiele, String> param) -> {
            // make cell containing buttons
            final TableCell<Materiele, String> cell = new TableCell<Materiele, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        Image image = new Image("ma/enset/projet/presentation/images/icons8-available-updates-27.png");
                        ImageView editIcon = new ImageView();
                        editIcon.setImage(image);

                        Image image1 = new Image("ma/enset/projet/presentation/images/icons8-delete-trash-28.png");
                        ImageView deleteIcon = new ImageView();
                        deleteIcon.setImage(image1);

                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            MultipleSelectionModel<Materiele> mat = matsTableView.getSelectionModel();
                            if(mat != null){
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setContentText("Veuillez vous supprimer ce Materielle ?");
                                Optional<ButtonType> optional = alert.showAndWait();
                                if (optional.get() == ButtonType.OK) {
                                    ms.deleteMat(mat.getSelectedItem());
                                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                                    alert1.setContentText("User a ete supprimer avec succes");
                                    alert1.show();
                                    loadMats();
                                }
                            } else{
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setContentText("Veuillez selectionner un element ! !");
                                alert.show();
                            }
                        });

                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            mat = matsTableView.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("../../../views/admin/materials/EditView.fxml"));

                            try {
                                loader.load();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }


                            EditController editController = loader.getController();
                            editController.setUpdate(true);
                            editController.setTextField(mat.getId(),mat.getNom(),mat.getCaracteristique());
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
                observableList.addAll(ms.searchMatByQuery(newValue));
            }
        });

        loadMats();
    }

    public void loadMats(){
        observableList.clear();
        observableList.addAll(ms.getAllMats());
    }

    @FXML
    void addMaterial(ActionEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("../../../views/admin/materials/AddView.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

package ma.enset.projet.presentation.controllers.user.tasks;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import ma.enset.projet.dao.TacheDaoImpl;
import ma.enset.projet.dao.entites.ResourceHumaine;
import ma.enset.projet.dao.entites.Tache;
import ma.enset.projet.services.TacheService;
import ma.enset.projet.services.TacheServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class TasksController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<Tache> tachesTableView;

    @FXML
    private TableColumn<Tache, String> colName;

    @FXML
    private TableColumn<Tache, String> colDesc;

    @FXML
    private TableColumn<Tache, ResourceHumaine> colManager;

    @FXML
    private TableColumn<Tache, Date> colStart;
    @FXML
    private TableColumn<Tache, String> colActions;

    @FXML
    private TableColumn<Tache, Date> colEnd;

    @FXML
    private TableColumn<Tache, Boolean> colStatu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    ObservableList<Tache> tacheObservableList = FXCollections.observableArrayList();
    TacheService ts;
    Tache tache;
    public void loadData(int id){
        ts = new TacheServiceImpl(new TacheDaoImpl());
        tacheObservableList.addAll(ts.tasksOfUser(id));
        tachesTableView.setItems(tacheObservableList);
        colName.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colStart.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        colEnd.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        colManager.setCellValueFactory(new PropertyValueFactory<>("projet"));
        colStatu.setCellValueFactory(new PropertyValueFactory<>("etat"));

        //add cell of button edit
        Callback<TableColumn<Tache, String>, TableCell<Tache, String>> cellFoctory = (TableColumn<Tache, String> param) -> {
            // make cell containing buttons
            final TableCell<Tache, String> cell = new TableCell<Tache, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        Image image = new Image("ma/enset/projet/presentation/images/icons8-task-planning-27.png");
                        ImageView editIcon = new ImageView();
                        editIcon.setImage(image);

                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            tache = tachesTableView.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("../../../views/user/tasks/EditView.fxml"));

                            try {
                                loader.load();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                            EditController editController = loader.getController();
                            editController.setTextField(tache.getId(),tache.getEtat());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();

                        });



                        HBox managebtn = new HBox(editIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));


                        setGraphic(managebtn);

                        setText(null);


                    }
                }

            };

            return cell;
        };
        colActions.setCellFactory(cellFoctory);
    }
}

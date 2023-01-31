package ma.enset.projet.presentation.controllers.admin.projects;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import ma.enset.projet.dao.ProjetDaoImpl;
import ma.enset.projet.dao.entites.Projet;
import ma.enset.projet.dao.entites.ResourceHumaine;
import ma.enset.projet.presentation.controllers.admin.users.EditController;
import ma.enset.projet.services.ProjetService;
import ma.enset.projet.services.ProjetServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ProjectsController implements Initializable {
    private Parent fxml;
    @FXML
    private AnchorPane root;
    Stage stage;
    int projectId;
    Projet projet;

    @FXML
    private TableView<Projet> projectsTableView;

    @FXML
    private TableColumn<Projet, Integer> colId;

    @FXML
    private TableColumn<Projet, String> colNom;

    @FXML
    private TableColumn<Projet, String> colActions;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @FXML
    private Button addProject;

    @FXML
    private Accordion accordion;


    @FXML
    void addProject(ActionEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("../../../views/admin/projects/AddView.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    ObservableList<Projet> projetObservableList = FXCollections.observableArrayList();
    ProjetService ps;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ps = new ProjetServiceImpl(new ProjetDaoImpl());
        /*
        projetObservableList.addAll(ps.getAllProjects());
        projectsTableView.setItems(projetObservableList);
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));

        //add cell of button edit
        Callback<TableColumn<Projet, String>, TableCell<Projet, String>> cellFoctory = (TableColumn<Projet, String> param) -> {
            // make cell containing buttons
            final TableCell<Projet, String> cell = new TableCell<Projet, String>() {
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
                            projet = projectsTableView.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("../../../views/admin/projects/ProjectDetailsView.fxml"));

                            try {
                                loader.load();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }



                            ProjectDetailController pdc = loader.getController();
                            //System.out.println(projectId);
                            pdc.setTxtPrjName(projet.getNom());
                            pdc.setProjectID(projet.getId());

                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                            //loadUsers();
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

         */


        initializeProjects();
    }

    List<Projet> projetList;

    void initializeProjects() {
        projetList = ps.getAllProjects();
        Projet prj;

        for (Projet projet : projetList) {
            // Gerarating TitledPane taking project information from database
            TitledPane titledpane = new TitledPane();
            titledpane.setText(projet.getNom());

            // VBox is used to place content inside TitledPane
            VBox content = new VBox();

            Label label = new Label("Project Name :    "+projet.getNom());
            label.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #2dfaff, #51ffc1);"+"-fx-text-fill: black;"+"-fx-font-style: oblique;"+"-fx-font-family: Corbel;");
            // Save the information in VBox for TitledPane to display
            content.getChildren().add(new Label("Project ID :    "+projet.getId()));
            content.getChildren().add(label);
            content.getChildren().add(new Label("Project Responsable :    "+projet.getResponsable().getNom()+" "+projet.getResponsable().getPrenom()));
            content.getChildren().add(new Label("Project Start Date :    "+projet.getDat_debut()));
            content.getChildren().add(new Label("Project End Date :    "+projet.getDat_fin()));


            content.getChildren().add(new Label("Estimated Time :    "+projet.getEstimated_time()+" Days"));
            JFXButton showporject = new JFXButton("Show Project Detail");
            showporject.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #2dfaff, #51ffc1);"+"-fx-text-fill: black;"+"-fx-font-style: oblique;"+"-fx-font-family: Corbel;");
            JFXButton deleteproject  = new JFXButton("Delete this project");
            content.getChildren().add(showporject);

            titledpane.setContent(content);

            // accordion is an object of Accordion Class
            accordion.getPanes().add(titledpane);

            //Projet finalPrj = projet;
            showporject.setOnAction((event) -> {

                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("../../../views/admin/projects/ProjectDetailsView.fxml"));

                try {
                    Loader.load();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // A ProjectDetailController object is created
                // to send information of the project detail page
                //ProjectDetailController projectDetailController = Loader.getController();
                ProjectDetailController pdc = Loader.getController();
                //System.out.println(projectId);
                pdc.setTxtPrjNom(projet.getNom());
                pdc.setProjectID(projet.getId());
                pdc.loadData(projet.getId());

                /*Parent p = Loader.getRoot();
                stage = (Stage) root.getScene().getWindow();
                Scene scene = new Scene(p);
                stage.setScene(scene);
                stage.show();

                 */
                Parent parent = Loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(parent));
                stage.initStyle(StageStyle.UTILITY);
                stage.show();


            });


        }

    }
}

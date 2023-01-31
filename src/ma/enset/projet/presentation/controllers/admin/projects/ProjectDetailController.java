package ma.enset.projet.presentation.controllers.admin.projects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import ma.enset.projet.dao.ProjetDaoImpl;
import ma.enset.projet.dao.SingletonConnexionDB;
import ma.enset.projet.dao.TacheDaoImpl;
import ma.enset.projet.dao.entites.Projet;
import ma.enset.projet.dao.entites.ResourceHumaine;
import ma.enset.projet.dao.entites.Tache;
import ma.enset.projet.presentation.controllers.admin.tasks.AddController;
import ma.enset.projet.presentation.controllers.admin.tasks.AddMaterialController;
import ma.enset.projet.presentation.controllers.admin.tasks.MaterialController;
import ma.enset.projet.services.ProjetService;
import ma.enset.projet.services.ProjetServiceImpl;
import ma.enset.projet.services.TacheService;
import ma.enset.projet.services.TacheServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class ProjectDetailController implements Initializable {

    private Parent fxml;
    int projectID;

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int id) {
        this.projectID = id;
    }

    @FXML
    private AnchorPane root;

    @FXML
    private Label txtPrjName;




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
    private TableColumn<Tache, Date> colEnd;

    @FXML
    private TableColumn<Tache, Boolean> colStatu;
    @FXML
    private TableColumn<Tache, String> colActions;
    @FXML
    private Button GanttChartButton;


    String txtPrjNom;

    public String getTxtPrjNom() {
        return txtPrjNom;
    }

    public void setTxtPrjNom(String txtPrjNom) {
        this.txtPrjNom = txtPrjNom;
        txtPrjName.setText(txtPrjNom);
    }



    @FXML
    private Button addTask;

    @FXML
    void addTask(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("../../../views/admin/tasks/AddView.fxml"));

        try {
            loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        AddController addController = loader.getController();
        addController.setProjetId(getProjectID());
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
        /*
        try {
            fxml = FXMLLoader.load(getClass().getResource("../../../views/admin/tasks/AddView.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

         */
    }

    ProjetService ps;
    Projet projet;
    ObservableList<Tache> tacheObservableList = FXCollections.observableArrayList();
    TacheService ts;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ps = new ProjetServiceImpl(new ProjetDaoImpl());
    }
    Tache tache;
    public void loadData(int id){
        ts = new TacheServiceImpl(new TacheDaoImpl());
        tacheObservableList.addAll(ts.tasksOfProject(id));
        tachesTableView.setItems(tacheObservableList);
        colName.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colStart.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        colEnd.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        colManager.setCellValueFactory(new PropertyValueFactory<>("responsable"));
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

                        Image image = new Image("ma/enset/projet/presentation/images/icons8-available-updates-27.png");
                        ImageView editIcon = new ImageView();
                        editIcon.setImage(image);

                        Image image1 = new Image("ma/enset/projet/presentation/images/icons8-delete-trash-28.png");
                        ImageView deleteIcon = new ImageView();
                        deleteIcon.setImage(image1);

                        Image image2 = new Image("ma/enset/projet/presentation/images/icons8-system-task-26.png");
                        ImageView materialsIcon = new ImageView();
                        materialsIcon.setImage(image2);


                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            MultipleSelectionModel<Tache> rh = tachesTableView.getSelectionModel();
                            if(rh != null){
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setContentText("Veuillez vous supprimer tache ?");
                                Optional<ButtonType> optional = alert.showAndWait();
                                if (optional.get() == ButtonType.OK) {
                                    ts.deleleTache(rh.getSelectedItem());
                                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                                    alert1.setContentText("tache a ete supprimer avec succes");
                                    alert1.show();
                                    loadTasks(id);
                                }
                            } else{
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setContentText("Veuillez selectionner un element ! !");
                                alert.show();
                            }
                        });

                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            tache = tachesTableView.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("../../../views/admin/tasks/addMaterialView.fxml"));

                            try {
                                loader.load();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }


                            AddMaterialController editController = loader.getController();
                            editController.setTaskId(tache.getId());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                            //loadUsers();
                        });

                        materialsIcon.setOnMouseClicked((MouseEvent event) -> {
                            tache = tachesTableView.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("../../../views/admin/tasks/MaterialsView.fxml"));

                            try {
                                loader.load();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }


                            MaterialController editController = loader.getController();
                            editController.setTaskId(tache.getId());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                            //loadUsers();
                        });




                        HBox managebtn = new HBox(editIcon, deleteIcon, materialsIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(materialsIcon, new Insets(2, 4, 0, 1));

                        setGraphic(managebtn);

                        setText(null);


                    }
                }

            };

            return cell;
        };
        colActions.setCellFactory(cellFoctory);
    }
    public void loadTasks(int id){
        tacheObservableList.clear();
        tacheObservableList.addAll(ts.tasksOfUser(id));
    }

    @FXML
    void deleteProjet(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Veuillez vous supprimer ce projet ?");
        Optional<ButtonType> optional = alert.showAndWait();
        if (optional.get() == ButtonType.OK) {
            ps.deleteProjet(getProjectID());
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setContentText("projet a ete supprimer avec succes");
            alert1.show();
        }

    }

    public void showGanttChart(ActionEvent event) {
        ArrayList<Date> ProjectDates = new ArrayList<java.util.Date>();
        List<Date> startDates = new ArrayList<java.util.Date>();
        List<java.util.Date> endDates = new ArrayList<java.util.Date>();
        ArrayList<String> TaskNames = new ArrayList<String>();
        ArrayList<String> TaskColors = new ArrayList<>();
        int count = 0;

        if(event.getSource() == GanttChartButton){

            try {

                Connection connection= SingletonConnexionDB.getConnection();

                Statement statement = connection.createStatement();
                String sql = "SELECT nom,date_debut,date_fin,color FROM tache WHERE id_projet = "+getProjectID();
                ResultSet rs = statement.executeQuery(sql);

                while (rs.next()) {
                    String TaskName = rs.getString("nom");
                    java.util.Date StartDate =  rs.getDate("date_debut");
                    java.util.Date EndDate = rs.getDate("date_fin");
                    String TaskColor = rs.getString("color");

                    TaskColors.add(toRGBCode(Color.valueOf(TaskColor)));
                    TaskNames.add(TaskName);
                    startDates.add(StartDate);
                    endDates.add(EndDate);
                    ++count;
                }
                rs.close();
                statement.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }

            try {
                Connection connection=SingletonConnexionDB.getConnection();

                Statement statement = connection.createStatement();
                String sql = "SELECT dat_debut,dat_fin FROM projet WHERE id="+getProjectID();
                ResultSet rs = statement.executeQuery(sql);

                while (rs.next()) {
                    java.util.Date StartDate =  rs.getDate("dat_debut");
                    java.util.Date EndDate = rs.getDate("dat_fin");
                    ProjectDates.add(StartDate);
                    ProjectDates.add(EndDate);
                }
                rs.close();
                statement.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }

            final DateAxis xAxis = new DateAxis();
            final CategoryAxis yAxis = new CategoryAxis();

            final GanttChartController<java.util.Date,String> chart = new GanttChartController<>(xAxis,yAxis);
            xAxis.setLabel("");
            xAxis.setTickLabelFill(Color.DARKRED);
            xAxis.setTickLabelGap(10);
            xAxis.setLowerBound(ProjectDates.get(0));
            xAxis.setUpperBound(ProjectDates.get(1));
            xAxis.averageTickGap();
            xAxis.setTickLength(15);
            xAxis.setMaxWidth(1000);
            xAxis.setMinWidth(1000);
            xAxis.setAutoRanging(false);
            xAxis.setTickLabelRotation(90);

            yAxis.setLabel("");
            yAxis.setTickLabelFill(Color.GREEN);
            yAxis.setTickLabelGap(10);
            yAxis.setCategories(FXCollections.observableList(TaskNames));

            //String prjName = ps.getProjetById(getProjectID()).getNom();
            chart.setTitle(getTxtPrjNom());
            chart.setLegendVisible(false);
            chart.setBlockHeight(50);

            for (int i =0; i<count; i++) {
                double length = xAxis.getDisplayPositionDate(startDates.get(i), endDates.get(i));
                XYChart.Series series = new XYChart.Series();
                series.getData().add(new XYChart.Data(startDates.get(i), TaskNames.get(i), new GanttChartController.ExtraData( length, TaskColors.get(i))));
                chart.getData().add(series);
            }

            chart.getStylesheets().add(getClass().getResource("GanttChart.css").toExternalForm());

            Scene scene  = new Scene(chart,1200,600);
            Stage stage = (Stage) GanttChartButton.getScene().getWindow();
            stage.setX(scene.getX()+50);
            stage.setScene(scene);
            stage.show();
        }
    }
    private String toRGBCode(Color color) {
        return String.format( "#%02X%02X%02X",
                (int)( color.getRed() * 255 ),
                (int)( color.getGreen() * 255 ),
                (int)( color.getBlue() * 255 ) );
    }

}

package ma.enset.projet.presentation.controllers.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import ma.enset.projet.dao.MaterielleDaoImpl;
import ma.enset.projet.dao.ProjetDaoImpl;
import ma.enset.projet.dao.ResourceHumaineDaoImpl;
import ma.enset.projet.services.*;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private Label rhNumber;
    @FXML
    private Label matNumber;

    @FXML
    private Label prjNumber;

    @FXML
    private PieChart chart;

    RhService rhs;
    MaterielleService ms;
    ProjetService ps;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rhs = new RhServiceImpl(new ResourceHumaineDaoImpl());
        ms = new MaterielleServiceImpl(new MaterielleDaoImpl());
        ps = new ProjetServiceImpl(new ProjetDaoImpl());
        rhNumber.setText(String.valueOf(rhs.countRh()));
        matNumber.setText(String.valueOf(ms.countMat()));
        prjNumber.setText(String.valueOf(ps.countProjects()));


        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Human Resources", rhs.countRh()),
                        new PieChart.Data("Material Resources", ms.countMat()),
                        new PieChart.Data("Projects", ps.countProjects())
                );
        chart.setData(pieChartData);
        chart.setTitle("Application Statistics");
        chart.setVisible(true);

    }
}

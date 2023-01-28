package ma.enset.projet.presentation.controllers.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import ma.enset.projet.dao.ResourceHumaineDaoImpl;
import ma.enset.projet.services.RhService;
import ma.enset.projet.services.RhServiceImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private Label rhNumber;
    @FXML
    private PieChart chart;

    RhService rhs;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rhs = new RhServiceImpl(new ResourceHumaineDaoImpl());
        rhNumber.setText(String.valueOf(rhs.countRh()));

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Human Resources", rhs.countRh()),
                        new PieChart.Data("Material Resources", 25),
                        new PieChart.Data("Projects", 10)
                );
        chart.setData(pieChartData);
        chart.setTitle("Application Statistics");
        chart.setVisible(true);

    }
}

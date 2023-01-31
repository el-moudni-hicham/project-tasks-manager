package ma.enset.projet.presentation.controllers.admin.tasks;

import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ma.enset.projet.dao.MaterielleDaoImpl;
import ma.enset.projet.dao.ProjetDaoImpl;
import ma.enset.projet.dao.ResourceHumaineDaoImpl;
import ma.enset.projet.dao.TacheDaoImpl;
import ma.enset.projet.dao.entites.Materiele;
import ma.enset.projet.dao.entites.Projet;
import ma.enset.projet.dao.entites.ResourceHumaine;
import ma.enset.projet.dao.entites.Tache;
import ma.enset.projet.services.*;
import org.controlsfx.control.CheckComboBox;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

public class AddController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField txtNom;

    @FXML
    private JFXTextField txtDescription;
    @FXML
    private Label txtProjet;

    @FXML
    private JFXDatePicker dateStart;

    @FXML
    private JFXDatePicker dateEnd;

    @FXML
    private JFXComboBox<ResourceHumaine> txtRespo;


    @FXML
    private JFXColorPicker txtColor;

    private int projetId;

    public int getProjetId() {
        return projetId;
    }

    public void setProjetId(int projetId) {
        this.projetId = projetId;
    }

    ObservableList<ResourceHumaine> resourceHumaineObservableList = FXCollections.observableArrayList();

    RhService rhs;
    MaterielleService ms;
    ProjetService ps;
    TacheService ts;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ps = new ProjetServiceImpl(new ProjetDaoImpl());
        rhs = new RhServiceImpl(new ResourceHumaineDaoImpl());
        ts = new TacheServiceImpl(new TacheDaoImpl());
        List<ResourceHumaine> resourceHumaineList = rhs.getAllRhs();
        resourceHumaineObservableList.addAll(resourceHumaineList);
        txtRespo.setItems(resourceHumaineObservableList);




        //Projet projet = ps.getProjetById(Integer.parseInt(txtProjet.getText()));
    }

    @FXML
    public void addTask(ActionEvent event) {
        if(txtNom.getText().isEmpty() || txtRespo.getValue().equals(null)|| dateStart.getValue().equals(null) || dateEnd.getValue().equals(null)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please complete all fields !");
            alert.show();
        }else {
            String nom = txtNom.getText();
            String desc = txtDescription.getText();
            Color cl = txtColor.getValue();
            String color = String.valueOf(cl);

            ResourceHumaine responsable = txtRespo.getValue();
            Projet projet = ps.getProjetById(getProjetId());

            LocalDate start = dateStart.getValue();
            int year = start.getYear();
            int month = start.getMonthValue();
            int day = start.getDayOfMonth();
            Date d_start = new Date(year, month, day);

            LocalDate fin = dateEnd.getValue();
            int year1 = fin.getYear();
            int month1 = fin.getMonthValue();
            int day1 = fin.getDayOfMonth();
            Date d_fin = new Date(year1, month1, day1);


            Tache tache = new Tache();
            tache.setNom(nom);
            tache.setDescription(desc);
            tache.setResponsable(responsable);
            tache.setProjet(projet);
            tache.setDate_debut(d_start);
            tache.setDate_fin(d_fin);
            tache.setColor(color);

            ts.addTache(tache);


            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Task added successfully");
            alert.show();

            try {
                Class.forName("javax.mail.internet.InternetAddress");
                Class.forName("javax.mail.internet.MimeMessage");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            final String username = "email";
            final String password = "password";

            Properties prop = new Properties();
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", "587");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.starttls.enable", "true");
            Session session = Session.getInstance(prop,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });


            try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("from@gmail.com"));
                message.setRecipients(
                        Message.RecipientType.TO,
                        InternetAddress.parse(responsable.getEmail())
                );
                message.setSubject("You Task in Project : " + projet.getNom());
                java.util.Date date = d_start;
                new java.sql.Date(date.getYear()-1900,date.getMonth()-1,date.getDate());

                message.setText("Task Name :  " + nom + "\n\nTask Details :  " + desc + "\nYour task start at : " + day +"/"+month+"/"+ year +  " and end at : " + day1 +"/"+month1+"/"+ year1);

                Transport.send(message);
            } catch (AddressException e) {
                throw new RuntimeException(e);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

package ma.enset.projet.presentation.controllers.admin.users;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import ma.enset.projet.dao.ResourceHumaineDaoImpl;
import ma.enset.projet.services.RhService;
import ma.enset.projet.services.RhServiceImpl;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailController {

    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField txtSubject;

    @FXML
    private TextArea txtMsg;

    int idRH;

    public int getIdRH() {
        return idRH;
    }

    public void setIdRH(int idRH) {
        this.idRH = idRH;
    }

    RhService rhs;
    @FXML
    void sendMail(ActionEvent event) {
        rhs = new RhServiceImpl(new ResourceHumaineDaoImpl());
        try {
            Class.forName("javax.mail.internet.InternetAddress");
            Class.forName("javax.mail.internet.MimeMessage");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //final String username = "hichamlmodni@gmail.com";
        //final String password = "pyoxoiuicfspilbd";
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
                    InternetAddress.parse(rhs.getRhById(getIdRH()).getEmail())
            );
            message.setSubject(txtSubject.getText());
            message.setText(txtMsg.getText());

            Transport.send(message);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Mail sended successfully ");
            alert.show();

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
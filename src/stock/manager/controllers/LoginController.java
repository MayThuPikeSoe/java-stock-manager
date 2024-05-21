/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.manager.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import stock.manager.dao.UserDAO;
import stock.manager.model.User;

/**
 *
 * @author Sithu
 */
public class LoginController implements Initializable {

    @FXML
    private JFXTextField usernameField;
    @FXML
    private JFXPasswordField passwordField;
    @FXML
    private JFXButton loginBtn;

    private UserDAO userDAO;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userDAO = new UserDAO();
    }

    @FXML
    private void authenticateUser(ActionEvent event) throws IOException {

        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Please fill out all fields.");
            alert.show();
            return;
        }

        try {

            User user = userDAO.findByUsername(username);
            if (user == null) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText("User does not exist.");
                alert.show();
                return;
            }

            if (user.getPassword().equals(password)) {
                Parent root = FXMLLoader.load(getClass().getResource("/stock/manager/views/main.fxml"));

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);

                Stage currentStage = (Stage) loginBtn.getScene().getWindow();
                currentStage.close();

                stage.show();
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText("Password incorrect.");
                alert.show();
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

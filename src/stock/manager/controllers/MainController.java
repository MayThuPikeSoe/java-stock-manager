/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.manager.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import static javafx.scene.paint.Color.color;

/**
 * FXML Controller class
 *
 * @author Sithu
 */
public class MainController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private Button dashboardBtn;
    @FXML
    private Button productsBtn;
    @FXML
    private AnchorPane dashboardView;
    @FXML
    private Button inoutBtn;
    @FXML
    private Button lowstockBtn;
    @FXML
    private Button transactionBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dashboardBtn.setStyle("-fx-background-color:#00bcd4");
    }    

    @FXML
    private void loadDashboardView(ActionEvent event) {
        borderPane.setCenter(dashboardView);
        dashboardBtn.setStyle("-fx-background-color:#00bcd4");
        productsBtn.setStyle("-fx-background-color:transparent");
        inoutBtn.setStyle("-fx-background-color:transparent");
        lowstockBtn.setStyle("-fx-background-color:transparent");
         transactionBtn.setStyle("-fx-background-color:transparent");
    }

    @FXML
    private void loadProductsView(ActionEvent event) throws IOException {
      Parent root =   FXMLLoader.load(getClass().getResource("/stock/manager/views/products.fxml"));
      borderPane.setCenter(root);
      productsBtn.setStyle("-fx-background-color:#00bcd4");
      dashboardBtn.setStyle("-fx-background-color:transparent");
      inoutBtn.setStyle("-fx-background-color:transparent");
      lowstockBtn.setStyle("-fx-background-color:transparent");
       transactionBtn.setStyle("-fx-background-color:transparent");
    }

    @FXML
    private void loadInOutView(ActionEvent event) throws IOException {
        Parent root =   FXMLLoader.load(getClass().getResource("/stock/manager/views/inout.fxml"));
      borderPane.setCenter(root);
      inoutBtn.setStyle("-fx-background-color:#00dcd4");
      dashboardBtn.setStyle("-fx-background-color:transparent");
      productsBtn.setStyle("-fx-background-color:transparent");
lowstockBtn.setStyle("-fx-background-color:transparent");
 transactionBtn.setStyle("-fx-background-color:transparent");
    }

    @FXML
    private void loadLowstockView(ActionEvent event) throws IOException {
     Parent root =   FXMLLoader.load(getClass().getResource("/stock/manager/views/lowstock.fxml"));
      borderPane.setCenter(root);
      lowstockBtn.setStyle("-fx-background-color:#00dcd4");
      productsBtn.setStyle("-fx-background-color:transparent");
      dashboardBtn.setStyle("-fx-background-color:transparent");
      inoutBtn.setStyle("-fx-background-color:transparent");
  transactionBtn.setStyle("-fx-background-color:transparent");
    }

    @FXML
    private void loadTransactionView(ActionEvent event) throws IOException {
        Parent root =   FXMLLoader.load(getClass().getResource("/stock/manager/views/transaction.fxml"));
        borderPane.setCenter(root);
        transactionBtn.setStyle("-fx-background-color:#00bcd4");
        dashboardBtn.setStyle("-fx-background-color:transparent");
        productsBtn.setStyle("-fx-background-color:transparent");
        inoutBtn.setStyle("-fx-background-color:transparent");
        lowstockBtn.setStyle("-fx-background-color:transparent");
        
    }
    
}

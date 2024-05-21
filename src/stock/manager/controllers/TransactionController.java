/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.manager.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import stock.manager.Util.MessageBox;
import stock.manager.dao.TransactionDAO;
import stock.manager.model.Transaction;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class TransactionController implements Initializable {

    @FXML
    private JFXDatePicker startDatepicker;
    @FXML
    private JFXDatePicker endDatePicker;
    @FXML
    private JFXButton submitBtn;
    @FXML
    private TableColumn<Transaction, Integer> idColumn;
    @FXML
    private TableColumn<Transaction, String> nameColumn;
    @FXML
    private TableColumn<Transaction, Integer> quantityColum;
    @FXML
    private TableColumn<Transaction, String> remarkColumn;
    private TransactionDAO transactionDAO;
    @FXML
    private TableView<Transaction> transactionTable;
    @FXML
    private TableColumn<Transaction, String> typeColumn;
    @FXML
    private TableColumn<Transaction, String> timeColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initColumns();
        transactionDAO = new TransactionDAO();
    }

   
    @FXML
    private void loadTransaction(ActionEvent event) {
        LocalDate startDate = startDatepicker.getValue();
        LocalDate endDate = endDatePicker.getValue();
        if (startDate == null || endDate == null) {
            MessageBox.showError("Error", "please fill out all.");
            return;
        }

        try {
            List<Transaction> transaction = transactionDAO.getTransactionByDate(Date.valueOf(startDate), Date.valueOf(endDate.plusDays(1)));
            transactionTable.getItems().setAll(transaction);
        } catch (SQLException ex) {
            Logger.getLogger(TransactionController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void initColumns() {

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        quantityColum.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        remarkColumn.setCellValueFactory(new PropertyValueFactory<>("remark"));
        quantityColum.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("transactionTime"));
    }

    
}

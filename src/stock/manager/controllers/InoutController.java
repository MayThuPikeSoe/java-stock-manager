/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.manager.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import stock.manager.Util.MessageBox;
import stock.manager.dao.ProductDAO;
import stock.manager.dao.TransactionDAO;
import stock.manager.model.Product;
import stock.manager.model.Transaction;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class InoutController implements Initializable {

    @FXML
    private JFXTextField productIdField;
    @FXML
    private JFXTextField quantityField;
    @FXML
    private JFXTextField remarkField;
    @FXML
    private RadioButton inRadio;
    @FXML
    private RadioButton outRadio;
    @FXML
    private JFXButton saveBtn;
    @FXML
    private ToggleGroup transactionTypeGroup;
    private ProductDAO productDAO;
    private TransactionDAO transactionDAO;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inRadio.setUserData("IN");
        outRadio.setUserData("OUT");
        productDAO = new ProductDAO();
        transactionDAO = new TransactionDAO();
    }

    @FXML
    private void saveTransaction(ActionEvent event) {
        String productIdStr = productIdField.getText();
        String quantityStr = quantityField.getText();
        String remark = remarkField.getText();
        String type = (String) transactionTypeGroup.getSelectedToggle().getUserData();

        if (productIdStr.isEmpty() || quantityStr.isEmpty() || remark.isEmpty()) {
            MessageBox.showError("Error", "Please fill out all fields");
           
            return;
        }

        try {
            int productId = Integer.parseInt(productIdStr);
            int quantity = Integer.parseInt(quantityStr);
            Product product = productDAO.getProduct(productId);
            if (product != null) {
                if (type.equals("IN")) {
                    int stock = product.getStock();
                    product.setStock(stock + quantity);
                    productDAO.updateProduct(product);
                } else {
                    int stock = product.getStock();
                    if (quantity > stock) {
                        MessageBox.showError("Error", "Quantity is greater than stock");
                        return;
                    } else {
                        product.setStock(stock - quantity);
                        productDAO.updateProduct(product);
                    }
                }
                Transaction transaction = new Transaction(type, productId, quantity, remark);
                transactionDAO.saveTransaction(transaction);
                MessageBox.showInformation("Success", " save");
                clearForm();
            } else {
                MessageBox.showError("Error", "Product does not exist");
            }
        } catch (NumberFormatException e) {
            MessageBox.showError("Error", "Invalid Number");
        } catch (SQLException ex) {
            Logger.getLogger(InoutController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private void clearForm(){
        productIdField.clear();
        quantityField.clear();
        remarkField.clear();
        inRadio.setSelected(true);
    }
}

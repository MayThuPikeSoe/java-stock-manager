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
import javafx.stage.Stage;
import stock.manager.dao.ProductDAO;
import stock.manager.model.Product;

/**
 * FXML Controller class
 *
 * @author Sithu
 */
public class NewproductController implements Initializable {

    @FXML
    private JFXTextField nameField;
    @FXML
    private JFXTextField idField;
    @FXML
    private JFXTextField priceField;
    @FXML
    private JFXButton cancelBtn;
    @FXML
    private JFXButton saveBtn;
    
    private ProductDAO productDAO;
    
    // declare
    
    private String myName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         productDAO = new ProductDAO();
    }    
    


    @FXML
    private void saveNewProduct(ActionEvent event) {
        System.out.println(myName);
        // Get Product Data
        String name = nameField.getText();
        String idStr = idField.getText();
        String priceStr = priceField.getText();
        
        
        
        // Validating
        if(name.isEmpty() || idStr.isEmpty() || priceStr.isEmpty()){
            System.out.println("Fill out all field.");
            return;
        }
        
        try{
            int id = Integer.parseInt(idStr);
            double price = Double.parseDouble(priceStr);
            // Save
            Product product = new Product(id, name, price, 0);
           
            productDAO.saveProduct(product);
            
            Stage currentStage = (Stage)saveBtn.getScene().getWindow();
            currentStage.close();
            
        }catch(NumberFormatException e){
            System.out.println("Invalid Number.");
        } catch (SQLException ex) {
            System.out.println("Duplicat Product ID.");
            Logger.getLogger(NewproductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }

    @FXML
    private void closeWindow(ActionEvent event) {
          Stage currentStage = (Stage)saveBtn.getScene().getWindow();
          currentStage.close();
    }
    
}

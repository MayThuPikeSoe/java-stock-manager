/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.manager.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import stock.manager.Util.MessageBox;
import stock.manager.dao.ProductDAO;
import stock.manager.model.Product;

/**
 * FXML Controller class
 *
 * @author Sithu
 */
public class ProductsController implements Initializable {

    @FXML
    private Button addBtn;
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, Integer> idColumn;
    @FXML
    private TableColumn<Product, String> nameColumn;
    @FXML
    private TableColumn<Product, Double> priceColumn;
    @FXML
    private TableColumn<Product, Integer> stockColumn;

    private ProductDAO productDAO;
    @FXML
    private MenuItem deleteItem;
    @FXML
    private JFXTextField productSearchField;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        productDAO = new ProductDAO();
        initColumns();
        productTable.setEditable(true);
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        priceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        loadTableData();
    }

    private void initColumns() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
    }

    private void loadTableData() {
        try {
            List<Product> productList = productDAO.getProducts();
            productTable.getItems().setAll(productList);
        } catch (SQLException ex) {
            Logger.getLogger(ProductsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void loadNewProductView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/stock/manager/views/newproduct.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        Stage mainStage = (Stage) addBtn.getScene().getWindow();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(mainStage);
        stage.showAndWait();
        loadTableData();
    }

    @FXML
    private void deleteProduct(ActionEvent event) {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            MessageBox.showError("Error", "Please select the product you want ro delete.");
            return ;
        }
        try {

            Optional<ButtonType> option = MessageBox.showAndWaitConfirmation("Confirm delete", "Are you sure you want to delete");
            if (option.get() == ButtonType.OK  ) {
                int rows = productDAO.deleteProduct(selectedProduct.getId());
                
                if (rows == 1) {
                    MessageBox.showInformation("Success", "Deleted.");
                }
                productTable.getItems().remove(selectedProduct);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void searchProductsByName(ActionEvent event) {
        String name = productSearchField.getText();
        try {
            List<Product> products = productDAO.getProductsByName(name);
            productTable.getItems().setAll(products);
        } catch (SQLException ex) {
            Logger.getLogger(ProductsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void updateProductName(TableColumn.CellEditEvent<Product, String> event)  {
        Product product=event.getRowValue();
        product.setName(event.getNewValue());
        try {
            productDAO.updateProduct(product);
        } catch (SQLException ex) {
            Logger.getLogger(ProductsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void updateProductPrice(TableColumn.CellEditEvent<Product, Double> event) {
       Product product=event.getRowValue();
        product.setPrice(event.getNewValue());  
        try {
            productDAO.updateProduct(product);
        } catch (SQLException ex) {
            Logger.getLogger(ProductsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


   
}

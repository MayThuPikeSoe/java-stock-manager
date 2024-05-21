/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.manager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import stock.manager.database.Database;

/**
 *
 * @author Sithu
 */
public class StockManager extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Database db = Database.getInstance();
        
        db.createDatabase();
        db.createTables();
        
        if(!db.isUserExists()){
            System.out.println("User does not exists.");
            db.createDefaultUser();
        }
        
        Parent root = FXMLLoader.load(getClass().getResource("/stock/manager/views/login.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

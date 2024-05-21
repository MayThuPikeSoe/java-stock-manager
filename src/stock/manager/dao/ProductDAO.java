/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.manager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import stock.manager.database.Database;
import stock.manager.model.Product;

/**
 *
 * @author Sithu
 */
public class ProductDAO {
    
    public int saveProduct(Product product) throws SQLException{
        
        Connection conn = Database.getInstance().getConnection();
        String sql = "insert into smdb.products (id,name,price,stock) value (?,?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, product.getId());
        stmt.setString(2,product.getName());
        stmt.setDouble(3, product.getPrice());
        stmt.setInt(4, product.getStock());
        int rows = stmt.executeUpdate();
        return rows;
    }
    
    public List<Product> getProducts() throws SQLException{
        Connection conn = Database.getInstance().getConnection();
        String sql = "select * from smdb.products";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(sql);
        List<Product> products = new ArrayList<>();
        while(result.next()){
           int id = result.getInt("id");
           String name = result.getString("name");
           double price = result.getDouble("price");
           int stock = result.getInt("stock");
           Product product = new Product(id, name, price, stock);
           products.add(product);
        }
        return products;
    }

    public int deleteProduct(int id) throws SQLException {
       Connection conn = Database.getInstance().getConnection();
        String sql = ("delete from smdb.products where id=?");

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        
        int rows = stmt.executeUpdate();
        return rows;
    }

    public List<Product> getProductsByName(String query) throws SQLException {
        Connection conn = Database.getInstance().getConnection();
        String sql = "select * from smdb.products where name like ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,"%"+query+"%");
        ResultSet result = stmt.executeQuery();
        List<Product> products = new ArrayList<>();
        while(result.next()){
           int id = result.getInt("id");
           String name = result.getString("name");
           double price = result.getDouble("price");
           int stock = result.getInt("stock");
           Product product = new Product(id, name, price, stock);
           products.add(product);
        }
        return products;
    }

    public int updateProduct(Product product) throws SQLException {
        Connection conn = Database.getInstance().getConnection();
       
        String sql = "update smdb.products set name=?,price=? ,stock=? where id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        
        stmt.setString(1, product.getName());
        
        stmt.setDouble(2, product.getPrice());
        stmt.setInt(3, product.getStock());
        stmt.setInt(4,product.getId());
        return stmt.executeUpdate();
    }
public Product getProduct(int productId) throws SQLException{
    Connection conn = Database.getInstance().getConnection();
       
        String sql = "select * from smdb.products where id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, productId);
        ResultSet result=stmt.executeQuery();
        Product product=null;
        if(result.next()){
            int id=result.getInt("id");
            String name=result.getString("name");
            double price=result.getDouble("price");
            int stock=result.getInt("stock");
            product=new Product(id,name,price,stock);
        }
        return product;
            
        }


 public List<Product> getLowStockProducts() throws SQLException{
        Connection conn = Database.getInstance().getConnection();
        String sql = "select * from smdb.products where stock <= 5";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(sql);
        List<Product> products = new ArrayList<>();
        while(result.next()){
           int id = result.getInt("id");
           String name = result.getString("name");
           double price = result.getDouble("price");
           int stock = result.getInt("stock");
           Product product = new Product(id, name, price, stock);
           products.add(product);
        }
        return products;
    }

}
    
    
    


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.manager.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Sithu
 */
public class Database {
    
    private String url = "jdbc:mysql://localhost:3306/";
    private String user = "root";
    private String password = "";
    
    
    
    private static Database db;
    private Connection conn;
    
    private Database() throws SQLException{
        connect();
    }
    
    public static Database getInstance() throws SQLException{
        if(db==null){
            db = new Database();
        }
        return db;
    }
    
    private void connect() throws SQLException{
        conn = DriverManager.getConnection(url, user, password);
    }
    
    public void createDatabase() throws SQLException{
        Statement stmt = conn.createStatement();
        String sql = "create database if not exists smdb";
        stmt.execute(sql);
    }
    
    
    public void createTables() throws SQLException{
        Statement stmt1 = conn.createStatement();
        String sql1 = "create table if not exists smdb.products (id int primary key,name varchar(60),price double,stock int)";
        stmt1.execute(sql1);
        
        Statement stmt2 = conn.createStatement();
        String sql2 = "create table if not exists smdb.users (id int primary key auto_increment,username varchar(60) unique,password varchar(60))";
        stmt2.execute(sql2);
        Statement stmt3 = conn.createStatement();
        String sql3 = "create table if not exists smdb.transaction (id int primary key auto_increment,type varchar(10),product_id int ,quantity int,remark varchar(255),transaction_time timestamp,foreign key(product_id) references products(id))";
        stmt3.execute(sql3);
    }
    
    public boolean isUserExists() throws SQLException{
        Statement stmt = conn.createStatement();
        String sql = "select count(*) as count from smdb.users";
        ResultSet result = stmt.executeQuery(sql);
        if(result.next()){
            int count = result.getInt("count");
            if(count==0){
                return false;
            }
        }
        return true;
    }
    
    public void createDefaultUser() throws SQLException{
        Statement stmt = conn.createStatement();
        String sql = "insert into smdb.users (username,password) value('admin','admin')";
        stmt.executeUpdate(sql);
    }

    public Connection getConnection() {
        return conn;
    }
    
    
}

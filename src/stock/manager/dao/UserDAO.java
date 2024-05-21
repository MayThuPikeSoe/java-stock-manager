/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.manager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import stock.manager.database.Database;
import stock.manager.model.User;

/**
 *
 * @author Sithu
 */
public class UserDAO {
    
    public User findByUsername(String name) throws SQLException{
        Connection conn = Database.getInstance().getConnection();
        
        String sql = "select * from smdb.users where username=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, name);
        ResultSet result = stmt.executeQuery();
        
        User user = null;
        
        if(result.next()){
            int id = result.getInt("id");
            String username = result.getString("username");
            String password = result.getString("password");
            user = new User(id, username, password);
        }
        return user;
    }
    
}

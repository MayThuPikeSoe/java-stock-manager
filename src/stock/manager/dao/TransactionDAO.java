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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import stock.manager.database.Database;
import stock.manager.model.Product;
import stock.manager.model.Transaction;

/**
 *
 * @author Dell
 */
public class TransactionDAO {

    public int saveTransaction(Transaction transaction) throws SQLException {
        Connection conn = Database.getInstance().getConnection();
        String sql = "insert into smdb.transaction(type,product_id,quantity,remark,transaction_time) value (?,?,?,?,now())";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, transaction.getType());
        stmt.setInt(2, transaction.getProductId());
        stmt.setInt(3, transaction.getQuantity());
        stmt.setString(4, transaction.getRemark());
        int rows = stmt.executeUpdate();
        return rows;

    }

    public List<Transaction> getTransactionByDate(Date startDate, Date endDate) throws SQLException {
        Connection conn = Database.getInstance().getConnection();
        String sql = "select * from smdb.transaction where transaction_time between ? and ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setDate(1, startDate);
        stmt.setDate(2, endDate);
        ResultSet result = stmt.executeQuery();
        ProductDAO productDAO = new ProductDAO();
        List<Transaction> transactionList = new ArrayList<>();
        while (result.next()) {
            int id = result.getInt("id");
            String type = result.getString("type");
            int productId = result.getInt("product_id");
            Product product = productDAO.getProduct(productId);
            String remark = result.getString("remark");
            int quantity = result.getInt("quantity");

            Timestamp time = result.getTimestamp("transaction_time");
            Transaction transaction = new Transaction(id, type, quantity, remark, time.toString(),product.getName());

            transactionList.add(transaction);
        }
        return transactionList;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.manager.model;

/**
 *
 * @author Dell
 */
public class Transaction {
    private int id;
    private String type;
    private int productId;
    private int quantity;
    private String remark;
    private String transactionTime;
    private String productName;

    public Transaction(int id, String type, int productId, int quantity, String remark, String transactionTime, String productName) {
        this.id = id;
        this.type = type;
        this.productId = productId;
        this.quantity = quantity;
        this.remark = remark;
        this.transactionTime = transactionTime;
        this.productName = productName;
    }

    public Transaction(int id, String type, int quantity, String remark, String transactionTime, String productName) {
        this.id = id;
        this.type = type;
        this.quantity = quantity;
        this.remark = remark;
        this.transactionTime = transactionTime;
        this.productName = productName;
    }

   
    
    
    
    public Transaction(String type, int productid, int quantity, String remark) {
        this.type = type;
        this.productId = productid;
        this.quantity = quantity;
        this.remark = remark;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductid(int productid) {
        this.productId = productid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    
    
}

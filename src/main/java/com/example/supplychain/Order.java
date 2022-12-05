package com.example.supplychain;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Order {
    void placeOrder(String productId) throws SQLException {
        ResultSet res=HelloApplication.connection.executeQuery("select max(orderId) from orders");
        int orderId=0;
        if(res.next()) {
            orderId = res.getInt("max(orderId)") + 1;
            String query = String.format("insert into orders values(%s,%s,'%s')", orderId, productId, HelloApplication.id);
            int response=HelloApplication.connection.executeUpdate(query);
            if(response>0){
                Dialog<String> dialog=new Dialog<>();
                dialog.setTitle("Order Confirmed");
                ButtonType type=new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                dialog.setContentText("Congrats! your order is booked");
                dialog.getDialogPane().getButtonTypes().add(type);
                dialog.showAndWait();
            }
        }
    }
}

package com.example.supplychain;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

public class sellerController {
    @FXML
    TextField name;
    @FXML
    TextField price;
    @FXML
    TextField sid;
    @FXML
    public void Add(MouseEvent event) throws SQLException {
        ResultSet res= HelloApplication.connection.executeQuery("select max(productId) from product_mstr;");
        int pId=0;
        if(res.next()){
            pId=res.getInt("max(productId)")+1;
        }
        String query=String.format("insert into product_mstr values(%s,'%s',%s,'%s');",pId,name.getText(),price.getText(),sid.getText());
        int response=HelloApplication.connection.executeUpdate(query);
        Dialog<String> dialog=new Dialog<>();
        dialog.setTitle("Product Add");
        ButtonType type=new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        if(response>0){
            dialog.setContentText("New Product added for sell!!");
        }
        else{
            dialog.setContentText("Error in adding the product!!!");
        }
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();

    }
}

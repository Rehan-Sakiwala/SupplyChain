package com.example.supplychain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductPage {
    ListView<HBox> products;

    ListView<HBox> showProductsByName(String searchtxt) throws SQLException {
        ObservableList<HBox> productList= FXCollections.observableArrayList();
        ResultSet res=HelloApplication.connection.executeQuery("select * from product_mstr");
        products=new ListView<>();
        Label Name=new Label();
        Label Price=new Label();
        Label Id=new Label();
        HBox Details=new HBox();
        Name.setMinWidth(50);
        Id.setMinWidth(50);
        Price.setMinWidth(50);
        Name.setText(" Name ");
        Price.setText(" Price ");
        Id.setText(" productId ");
        Details.getChildren().addAll(Id,Name,Price);
        productList.add(Details);
        while(res.next()){
          if(res.getString("productName").toLowerCase().contains(searchtxt.toLowerCase())) {
              Label productName = new Label();
              Label productPrice = new Label();
              Label productId = new Label();
              Button buy = new Button();
              HBox productDetails = new HBox();
              productName.setMinWidth(50);
              productId.setMinWidth(50);
              productPrice.setMinWidth(50);
              buy.setText("Buy");

              buy.setOnAction(new EventHandler<ActionEvent>() {
                  @Override
                  public void handle(ActionEvent actionEvent) {
                      if (HelloApplication.id.equals("")) {
                          Dialog<String> dialog = new Dialog<>();
                          dialog.setTitle("Login");
                          ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                          dialog.setContentText("Please login to proceed further!!!");
                          dialog.getDialogPane().getButtonTypes().add(type);
                          dialog.showAndWait();
                      } else {
                          Order place = new Order();
                          try {
                              place.placeOrder(productId.getText());
                          } catch (SQLException e) {
                              throw new RuntimeException(e);
                          }
                      }
                  }
              });

              productName.setText(res.getString("productName"));
              productPrice.setText(res.getString("price"));
              productId.setText(res.getString("productId"));
              productDetails.getChildren().addAll(productId, productName, productPrice, buy);
              productList.add(productDetails);
          }
        }
        if(productList.size()==1){
            Dialog<String> dialog=new Dialog<>();
            dialog.setTitle("Search Result");
            ButtonType type=new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("No such product found!!!");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
        }
        products.setItems(productList);
        return products;
    }

    ListView<HBox> showProducts() throws SQLException {
        ObservableList<HBox> productList= FXCollections.observableArrayList();
        ResultSet res=HelloApplication.connection.executeQuery("select * from product_mstr");
        products=new ListView<>();
        Label Name=new Label();
        Label Price=new Label();
        Label Id=new Label();
        HBox Details=new HBox();
        Name.setMinWidth(50);
        Id.setMinWidth(50);
        Price.setMinWidth(50);
        Name.setText(" Name ");
        Price.setText(" Price ");
        Id.setText(" productId ");
        Details.getChildren().addAll(Id,Name,Price);
        productList.add(Details);
        while(res.next()){
            Label productName=new Label();
            Label productPrice=new Label();
            Label productId=new Label();
            Button buy=new Button();
            HBox productDetails=new HBox();
            productName.setMinWidth(50);
            productId.setMinWidth(50);
            productPrice.setMinWidth(50);
            buy.setText("Buy");

            buy.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if(HelloApplication.id.equals("")){
                        Dialog<String> dialog=new Dialog<>();
                        dialog.setTitle("Login");
                        ButtonType type=new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                        dialog.setContentText("Please login to proceed further!!!");
                        dialog.getDialogPane().getButtonTypes().add(type);
                        dialog.showAndWait();
                    }
                    else{
                        Order place=new Order();
                        try {
                            place.placeOrder(productId.getText());
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            });

            productName.setText(res.getString("productName"));
            productPrice.setText(res.getString("price"));
            productId.setText(res.getString("productId"));
            productDetails.getChildren().addAll(productId,productName,productPrice,buy);
            productList.add(productDetails);
        }
        products.setItems(productList);
        return products;
    }
}

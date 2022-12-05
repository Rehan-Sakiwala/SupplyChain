package com.example.supplychain;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class LoginPageController {
    @FXML
    TextField email;
    @FXML
    PasswordField password;

    @FXML
    public void login(MouseEvent event) throws SQLException, IOException {
       String query=String.format("select * from user_mstr where emailId='%s' and pwd='%s'",email.getText(),password.getText());
       ResultSet res=HelloApplication.connection.executeQuery(query);
       if(res.next()){
           String userType=res.getString("userType");
           HelloApplication.id=res.getString("emailId");
           if(userType.equals("Buyer")){
               ProductPage products=new ProductPage();
               Header header=new Header();
               ListView<HBox> productList= products.showProducts();

               AnchorPane productPane=new AnchorPane();
               productPane.setLayoutX(120);
               productPane.setLayoutY(50);
               productPane.getChildren().add(productList);
               HelloApplication.root.getChildren().clear();
               HelloApplication.root.getChildren().addAll(header.root,productPane);

           }
           else{
               AnchorPane sellerPage= FXMLLoader.load((Objects.requireNonNull(getClass().getResource("seller.fxml"))));
               HelloApplication.root.getChildren().add(sellerPage);
           }
       }
       else{
           Dialog<String> dialog=new Dialog<>();
           dialog.setTitle("Login");
           ButtonType type=new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
           dialog.setContentText("Error Logging in!!!");
           dialog.getDialogPane().getButtonTypes().add(type);
           dialog.showAndWait();
       }
    }

}

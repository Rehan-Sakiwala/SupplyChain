package com.example.supplychain;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class HeaderController {
    @FXML
    Button loginbtn;
    @FXML
    Label uid;
    @FXML
    TextField searchtxt;

    @FXML
    Button logoutbtn;

    @FXML
    public void initialize(){
        if(!HelloApplication.id.equals("")){
            loginbtn.setOpacity(0);
            uid.setText("Hii "+HelloApplication.id);
        }
    }

    public void login(MouseEvent mouseEvent) throws IOException {
        AnchorPane loginpage= FXMLLoader.load((getClass().getResource("LoginPage.fxml")));
        HelloApplication.root.getChildren().add(loginpage);
    }

    public void search(MouseEvent event) throws SQLException, IOException {
        Header header=new Header();
        ProductPage products=new ProductPage();
        AnchorPane ProductPage=new AnchorPane();
        ProductPage.getChildren().add(products.showProductsByName(searchtxt.getText()));
        ProductPage.setLayoutX(120);
        ProductPage.setLayoutY(50);
        HelloApplication.root.getChildren().clear();
        HelloApplication.root.getChildren().addAll(header.root,ProductPage);
    }

    public void logout(MouseEvent mouseEvent) {
        if(logoutbtn.getOpacity()==0)
        {
            logoutbtn.setOpacity(1);
            logoutbtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    HelloApplication.id="";
                    logoutbtn.setOpacity(0);
                    try {
                        Header header=new Header();
                        HelloApplication.root.getChildren().add(header.root);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
            });
        }
        else
            logoutbtn.setOpacity(0);

    }
}

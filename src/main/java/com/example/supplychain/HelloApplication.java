package com.example.supplychain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloApplication extends Application {

    public static Group root;
    public static DbConnection connection;
    public static String id;

    @Override
    public void start(Stage stage) throws IOException, SQLException {
        id="";
        connection=new DbConnection();
        root = new Group();
        Header header=new Header();
        ProductPage products=new ProductPage();
        ListView<HBox> productList= products.showProducts();
        AnchorPane productPane=new AnchorPane();
        productPane.setLayoutX(120);
        productPane.setLayoutY(50);
        productPane.getChildren().add(productList);
        root.getChildren().addAll(header.root,productPane);
        stage.setTitle("Supply Chain");
        stage.setScene(new Scene(root,500,500));
        stage.show();
        stage.setOnCloseRequest(e->{
            try {
                connection.con.close();
                System.out.println("Connection closed!!!");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}
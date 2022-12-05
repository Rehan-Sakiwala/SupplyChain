package com.example.supplychain;
import java.net.SocketOption;
import java.sql.*;

public class DbConnection {
    String SQLURL="jdbc:mysql://localhost:3306/supply?allowPublicKeyRetrieval=true&useSSL=false";
    String user="root";
    String pwd="sqll00";
    Connection con=null;
    DbConnection() throws SQLException {
        con = DriverManager.getConnection(SQLURL,user,pwd);
        if(con!=null){
            System.out.println("Success!!!");
        }
    }
    public ResultSet executeQuery(String query) throws SQLException {
        Statement statement=con.createStatement();
        ResultSet res=statement.executeQuery(query);
        return res;
    }

    public int executeUpdate(String query) throws SQLException{
        Statement statement=con.createStatement();
        int res=statement.executeUpdate(query);
        return res;
    }
}

package banking;

import java.sql.Connection;
import java.sql.DriverManager;

class Connector{
    public static Connection getConnect(){
        try {
            String url = "jdbc:mysql://localhost:3306/bank_Project";
            String user = "root";
            String pass = "macintosh1";
            System.out.println("DataBase Connection Successfull...");
            return DriverManager.getConnection(url, user, pass);
        }catch(Exception e){
            System.out.println("Error: Database Connection Failed --> ");
            e.printStackTrace();
        }
        return null;
    }
}
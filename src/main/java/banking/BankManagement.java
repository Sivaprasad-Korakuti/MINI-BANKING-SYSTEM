package banking;

import java.sql.*;
import java.util.Scanner;
class BankManagement{
   static Scanner sc = new Scanner(System.in);
   static Connection con = Connector.getConnect();


   /*
            BANK DATA MODIFICATION METHODS
    */

   public static void depositAmount(String name, int amount) throws Exception
   {
      String query1 = "UPDATE customer SET balance = balance + ? where cname = ?";
      PreparedStatement pst1 = con.prepareStatement(query1);
      pst1.setInt(1, amount);
      pst1.setString(2,name);
      int row = pst1.executeUpdate();
      System.out.println(amount+ " Deposited successfully to the user: "+name);
      pst1.close();
   }
   public static void withdrawAmmount(String name, int amount)throws Exception
   {
      String query2 = "UPDATE customer SET balance = balance - ? where cname = ?";
      PreparedStatement pst2 = con.prepareStatement(query2);
      pst2.setInt(1, amount);
      pst2.setString(2,name);
      int rs = pst2.executeUpdate();
      System.out.println(amount + " : Withdrwan successfully from the bank account of "+name);
   }
   public static void viewBalance(String name) throws Exception
   {
      String query3 = "SELECT * FROM customer WHERE cname = ?";
      PreparedStatement pst3 = con.prepareStatement(query3);
      pst3.setString(1,name);
      ResultSet rs1 = pst3.executeQuery();
      System.out.println("**************************************** BANK ACCOUNT *****************************************");
      System.out.println("\n\t\t\t\tAC_NO  |  NAME | BALANCE ");
      while(rs1.next()){
         System.out.println("\t\t\t\t"+rs1.getInt("ac_no")+"  |  "+rs1.getString("cname")+"  |  "+rs1.getInt("balance"));
      }
      System.out.println("***********************************************************************************************");
      rs1.close();
      pst3.close();
   }

   /*
         BANK AUTHENTICATION AND IDENTIFICATION METHODS
    */
   //                               CREATE ACCOUNT
   public static void createAccount() throws Exception{

       System.out.println("Enter Name: ");
       String name = sc.nextLine();
       System.out.println("Enter Password: ");
       int pass = sc.nextInt();

       if(con != null){
         System.out.println("DATABASE CONNECtion SUCCESSFULL...");
         }
       String query = "INSERT INTO CUSTOMER(cname, balance, pass_code) VALUES(?,1000,?)";

       PreparedStatement pst = con.prepareStatement(query);

       pst.setString(1,name);
       pst.setInt(2,pass);
       int row = pst.executeUpdate();
       System.out.println(row+" user created successfully...\n");

       pst.close();
   }
   //                            LOGIN ACCOUNT
   public static void loginAccount()throws Exception {
      System.out.println("Enter the customer name: ");
      String name = sc.nextLine();

      System.out.println("Enter Password:");
      int pass = sc.nextInt();

      String query = "SELECT * FROM customer WHERE cname= ?";
      PreparedStatement pst = con.prepareStatement(query);
      pst.setString(1, name);

      ResultSet rs = pst.executeQuery();
      if (rs.next()) {
         System.out.println("Login Successfull...");
         System.out.println("WELCOME " + rs.getString("cname"));
      } else {
         System.out.println("Login failed due to wrong credentials either user name or password");
         return;
      }
      System.out.println("Choose method: \n1. Deposit\n2. Withdraw\n3.view Balance\n4.Update passCode");
      int choice = sc.nextInt();
      switch (choice) {
         case 1 -> {
            System.out.println("Enter amount to Deposit:");
            int amount = sc.nextInt();
            BankManagement.depositAmount(name, amount);
            BankManagement.viewBalance(name);
         }
         case 2 -> {
            System.out.println("Enter amount to Deposit:");
            int amount = sc.nextInt();
            BankManagement.withdrawAmmount(name, amount);
            BankManagement.viewBalance(name);
         }
         case 3 -> BankManagement.viewBalance(name);
         case 4 -> {
               System.out.println("Enter new Pass Code: ");
               int new_pass = sc.nextInt();
               String updateQuery = "UPDATE customer set pass_code = ? where cname = ? ";
            PreparedStatement pst4 = con.prepareStatement(updateQuery);

            pst4.setInt(1, new_pass);
            pst4.setString(2, name);

               int row = pst4.executeUpdate();
            System.out.println("Pass Code Update Successfull for "+name);
            pst.close();
      }
   }
      con.close();
   }
}
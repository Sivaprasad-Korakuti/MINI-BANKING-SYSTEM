package banking;
import java.util.Scanner;
import java.sql.Connection;


public class Bank{
    static void main() throws Exception{
            Scanner sc = new Scanner(System.in);
                System.out.println("============================== MINI BANKING SYSTEM ==============================");
                System.out.println("Choose one of the options: \n1. Creae Account\n2. Login\n3 Exit");
                System.out.println("==================================================================================");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1 -> {
                        BankManagement.createAccount();
                    }
                    case 2 -> {
                        BankManagement.loginAccount();
                    }
                    case 3 -> {
                        System.exit(0);
                    }
                    default -> {
                        System.out.println("Invalid choice!");
                    }
                }
    }
}
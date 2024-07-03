import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        boolean running = true;
        int choice=0;
        try{
            while (running) {
                choice=ConsoleMenu.displayloginmenu();
                switch (choice){
                    case 1:
                        int UID = ConsoleMenu.usersigninprocess();
                        if (UID==0){ System.out.println("ERROR: Invalid Username or Password."); break; }
                        else ConsoleMenu.displaytaskmenu(UID);
                        break;
                    case 2:
                        running = false;
                        break;
                    default:
                        System.out.println("Wrong choice. Try again.");
                        break;
                }
            }
        }
        catch (SQLException e){
            System.out.println("Database error occurred: " + e.getMessage());
        }
        catch (Exception e){
            System.out.println("Error Occurred. Please inform Admin.");
            System.out.println("Exception Encountered: " + e.getMessage());
        }
    }
}
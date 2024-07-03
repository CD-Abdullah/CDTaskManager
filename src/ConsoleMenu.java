import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsoleMenu {

    // Signin Functional Interface Implementation
    static Signin sn = (UName,UPass) -> {
        String sql = "SELECT \"UID\" FROM \"Users\" WHERE \"UName\"=? AND \"UPass\"=?";
        try(Connection con = dbconnect.open();
            PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setString(1, UName);
            pstmt.setString(2, UPass);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return Integer.parseInt(rs.getString(1));
            }
            dbconnect.close(con);
            return 0;
        }
        catch (SQLException e){
            System.out.println("Error Occurred. Please inform Admin.");
            System.out.println("SQL Exception: " + e.getMessage());

            return 0;
        }
    };

    public static int displayloginmenu(){
        System.out.println("================ TASK MANAGER ================");

        System.out.println("Press 1- Login");
        System.out.println("Press 2- Exit");
        int choice=GetInput.getint();
        return choice;
    }
    public static void displaytaskmenu(int UID) throws SQLException {
        boolean subrunning = true;
        int subchoice=0;
        while (subrunning) {
            System.out.println("================ WELCOME ================");
            System.out.println("Press 1- View Tasks");
            System.out.println("Press 2- Add Tasks");
            System.out.println("Press 3- Update Tasks");
            System.out.println("Press 4- Delete Tasks");
            System.out.println("Press 5- Logout");
            subchoice=GetInput.getint();

            switch (subchoice){
                case 1:
                    PerformOperations.Tview(UID);
                    System.out.print("\n");
                    break;
                case 2:
                    try{
                        Tasks tasks = new Tasks();
                        tasks=tasks.AddTasks(UID);
                        PerformOperations.Tadd(tasks);
                        System.out.print("\n");
                        break;
                    }
                    catch(SQLException e){
                        System.out.println("Error Occurred. Please inform Admin.");
                    }

                case 3:
                    PerformOperations.Tview(UID);
                    System.out.print("\n");
                    PerformOperations.TUpdate(UID);
                    break;
                case 4:
                    PerformOperations.Tview(UID);
                    System.out.print("\n");
                    PerformOperations.Tdelete(UID);
                    break;
                case 5:
                    subrunning=false;
                    System.out.print("\n");
                    break;
                default:
                    System.out.println("Wrong choice. Try again.");
                    break;
            }
        }
    }
    public static int usersigninprocess() throws SQLException {
        System.out.print("Input Username: ");
        String UName=GetInput.getString();
        System.out.print("Input Password: ");
        String UPass=GetInput.getString();
        int UID=0;
        UID = sn.signin(UName,UPass);
        return UID;
    }
}

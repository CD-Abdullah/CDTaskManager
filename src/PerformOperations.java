import java.sql.*;


public class PerformOperations {

    static Connection con = null;
    static PreparedStatement ps =null;
    static ResultSet rs=null;

    public static void Tadd(Tasks tasks) throws SQLException {
        try {
            con = dbconnect.open();
            ps = con.prepareStatement("INSERT INTO \"Tasks\" (\"UID\", \"TTitle\", \"TDesc\") VALUES (?,?,?)");
            ps.setInt(1, tasks.UID);
            ps.setString(2, tasks.TTitle);
            ps.setString(3, tasks.TDesc);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error Occurred. Please inform Admin.");
            System.out.println("Error adding task: " + e.getMessage());
            throw new SQLException(e);
        }
        finally{
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null)  dbconnect.close(con);
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
                throw new SQLException(e);
            }
        }

    }
    public static void Tview(int UID) throws SQLException {
        Connection con = dbconnect.open();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT \"TID\", \"TTitle\", \"TDesc\" FROM \"Tasks\" WHERE \"UID\" = ?");
            ps.setInt(1, UID);
            ResultSet rs = ps.executeQuery();
            System.out.println(" ID   | Title           | Description  ");
            while (rs.next()) {
                System.out.println(" " + rs.getInt("TID") + "    | " + rs.getString("TTitle") + "      | " + rs.getString("TDesc"));
            }
        } catch (SQLException e) {
            System.out.println("Error Occurred. Please inform Admin.");
            System.out.println("Error viewing tasks: " + e.getMessage());
        }
        dbconnect.close(con);
    }
    public static void Tdelete(int UID) throws SQLException {
        Connection con = dbconnect.open();
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM \"Tasks\" WHERE \"TID\" = ? AND \"UID\" = ?");
            System.out.print("Enter Task ID to delete: ");
            int taskId = GetInput.getint();

            ps.setInt(1, taskId);
            ps.setInt(2, UID);
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Task Deleted Successfully!");
            } else {
                System.out.println("No task found with the specified ID for this user.");
            }
        } catch (SQLException e) {
            System.out.println("Error Occurred. Please inform Admin.");
            System.out.println("Error deleting task: " + e.getMessage());

        }
        dbconnect.close(con);
    }
    public static void TUpdate(int UID) throws SQLException {
        Connection con = dbconnect.open();
        try {

            System.out.print("Enter Task ID to update: ");
            int taskId = GetInput.getint();
            //Check if TID exists
            PreparedStatement checkPs = con.prepareStatement("SELECT COUNT(*) AS count FROM \"Tasks\" WHERE \"TID\" = ? AND \"UID\" = ?");
            checkPs.setInt(1, taskId);
            checkPs.setInt(2, UID);
            ResultSet rs = checkPs.executeQuery();
            int count = 0;
            if (rs.next()) {
                count = rs.getInt("count");
            }

            if (count == 0) {
                System.out.println("No task found with the specified ID for this user.");
                return;
            }

            boolean subrunning = true;
            int subchoice=0;
            int affectedRows = 0;

            System.out.println("Select Update to Make");
            while (subrunning) {

                System.out.println("Press 1- Update Title");
                System.out.println("Press 2- Update Description");
                System.out.println("Press 3- Return Without Updating");

                subchoice=GetInput.getint();

                switch (subchoice){
                    case 1:
                        System.out.print("Enter new Title: ");
                        String newTitle = GetInput.getString();
                        PreparedStatement ps = con.prepareStatement("UPDATE \"Tasks\" SET \"TTitle\" = ? WHERE \"TID\" = ? AND \"UID\" = ?");
                        ps.setString(1, newTitle);
                        ps.setInt(2, taskId);
                        ps.setInt(3, UID);
                        affectedRows = ps.executeUpdate();
                        subrunning = false;
                        break;
                    case 2:
                        System.out.print("Enter new Description: ");
                        String newDescription = GetInput.getString();
                        PreparedStatement pss = con.prepareStatement("UPDATE \"Tasks\" SET \"TDesc\" = ? WHERE \"TID\" = ? AND \"UID\" = ?");
                        pss.setString(1, newDescription);
                        pss.setInt(2, taskId);
                        pss.setInt(3, UID);
                        affectedRows = pss.executeUpdate();
                        subrunning = false;
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Wrong choice. Try again.");
                        break;
                }
            }
            //===========================================

            if (affectedRows > 0) {
                System.out.println("Task Updated Successfully!");
            } else {
                System.out.println("Error Updating Task.");
            }
        } catch (SQLException e) {
            System.out.println("Error Occurred. Please inform Admin.");
            System.out.println("Error updating task: " + e.getMessage());
        }
        dbconnect.close(con);
    }
}

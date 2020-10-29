package Controllers;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class DatabaseDriver {

    private static Statement stmt;

    /**
     * This static class acts as the bridge between the Java application and the
     * Access Database that stores all the related data for it. It executes all
     * the queries for the application
     */
    static {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            stmt = DriverManager.getConnection("jdbc:ucanaccess://DetentionDB.accdb;").createStatement();
            System.out.println("Connection successful");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns the results of a SQL Statement in the DonationManager.accdb
     * Database contained in the form of a ResultSet
     *
     * @param sql Statement
     * @return ResultSet with query results
     */
    public static ResultSet query(String sql) {
        System.out.println(sql + "\n");
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL Exception\n" + ex, "Invalid Input Error", ERROR_MESSAGE);
        }
        return rs;
    }

    /**
     * Executes an insert query in DonationManager.accdb
     *
     * @param sql
     * @return True if insert was successful and false if not
     */
    public static boolean insert(String sql) {
        System.out.println(sql + "\n");
        try {
            stmt.execute(sql);
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL Exception\n" + ex, "Invalid Input Error", ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Executes an update query in DonationManager.accdb
     *
     * @param sql
     * @return Either the row count of the query or 0 if the update was
     * unsuccessful
     */
    public static int update(String sql) {
        System.out.println(sql + "\n");
        int result = 0;
        try {
            result = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL Exception\n" + ex, "Invalid Input Error", ERROR_MESSAGE);
        }
        return result;
    }
}

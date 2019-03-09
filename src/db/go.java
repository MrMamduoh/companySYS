//we created this package and this class to make all work required for connecting java to mySQL db
package db;

import company.Tools;
import company.Tools.Table;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class go {

    private static String url = "";
    //Import Java.sql.Connection only
    private static Connection connect;

    private static void setUrl() {
        //3306 mysql port , company is db Name 
        url = "jdbc:mysql://localhost:3306/company"
                + "?useUnicode=true&characterEncoding=UTF-8";
    }

    private static void setConnection() {
        try {
            setUrl();
            //Root is my mySQL User , password empty so it's empty string here 
            connect = DriverManager.getConnection(url, "root", "");
        } catch (SQLException ex) {
            Tools.msgBox(ex.getMessage());
        }
    }

    public static boolean checkuserAndPass(String User, String Pass) {
        try {
            setConnection();
            Statement stmt = connect.createStatement();
            String Check = "select * from users where username='" + User + "' and password = '" + Pass + "'";
            stmt.executeQuery(Check);
            // يعني لو الجمله رجعت بيانات كمل لان معني كده المدخلات صحيحع
            while (stmt.getResultSet().next()) {
                connect.close();
                return true;
            }
            connect.close();
        } catch (Exception ex) {
            Tools.msgBox(ex.getMessage());
        }
        return false;
    }

    //Method for update/add/delete from database 
    public static boolean runNonQuery(String SQLStatement) {
        try {
            setConnection();
            Statement stmt = connect.createStatement();
            stmt.execute(SQLStatement);
            connect.close();
            return true; // in case this SQLStatement succesfully excuted return true
        } catch (SQLException ex) {
            Tools.msgBox(ex.getMessage());
        }
        return false;
    }

    public static String getAutoNum(String tableName, String columnName) {
        try {
            setConnection();
            Statement stmt = connect.createStatement();
            String query = "SELECT MAX(" + columnName + ")+1 as autoNum FROM" + tableName;
            stmt.execute(query);
            String num = "";
            while (stmt.getResultSet().next()) {
                num = stmt.getResultSet().getString("autoNum");
            }
            connect.close();
            if (num == null || "".equals(num)) {
                return "1";
            } else {
                return num;
            }
        } catch (SQLException ex) {
            Tools.msgBox(ex.getMessage());
            return "";
        }

    }

    public static Table getTableData(String Statement) {
        Tools t = new Tools();
        try {
            setConnection();
            Statement stmt = connect.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery(Statement);
            //ResultSetMetaData for getting column data 
            ResultSetMetaData rsmd = rs.getMetaData();
            int c = rsmd.getColumnCount();
            Table table = t.new Table(c);
            while (rs.next()) {
                Object[] row = new Object[c];
                for (int i = 0; i < c; i++) {
                    row[i] = rs.getString(i + 1);
                }
                table.addNewRow(row);
            }
            connect.close();
            return table;
        } catch (SQLException ex) {
            Tools.msgBox(ex.getMessage());
            return t.new Table(0);
        }
    }

    public static void fillToJTable(String TableNameOrSelectStatement, JTable table) {
        try {
            setConnection();
            Statement stmt = connect.createStatement();
            ResultSet rs;
            String SPart = TableNameOrSelectStatement.substring(0, 7).toLowerCase(); // Here we will check if the first 7 characters = select or not 
            String SelectOrTable;
            if ("select".equals(SPart)) {
                SelectOrTable = TableNameOrSelectStatement; // that means user entered select statement  
            } else {
                SelectOrTable = "select  * from " + TableNameOrSelectStatement; // that means user entered TableName
            }
            rs = stmt.executeQuery(SelectOrTable);
            ResultSetMetaData rsmd = rs.getMetaData();  //ResultSetMetaData retrive info from column
            int c = rsmd.getColumnCount(); // to get co;umn number 
            DefaultTableModel Model = (DefaultTableModel) table.getModel();
            Vector row = new Vector();

            Model.setColumnCount(0); // To Reset All Column in the Tabe (Will Delete any rows if exists)
            while (rs.next()) {
                row = new Vector(c);
                for (int i = 1; i <= c; i++) {
                    row.add(rs.getString(i));
                }
                Model.addRow(row);
            }
            if (table.getColumnCount() != c) {
                Tools.msgBox("JTable Columns Count Doesn't Equal Query Columns count !" + table.getColumnCount() + "\n Query Columns count Is : " + c);
            }
            connect.close();
        } catch (SQLException ex) {
            Tools.msgBox(ex.getMessage());
        }
    }

}

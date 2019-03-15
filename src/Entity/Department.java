package Entity;

import company.Tools;
import javax.swing.JTable;

public class Department implements MainData {
    // Class atributes
    private int DEPT_NO;
    private String Dept_Name;
    private String Location;
    
    public int getDEPT_NO() {
        return DEPT_NO;
    }
    
    public void setDEPT_NO(int DEPT_NO) {
        this.DEPT_NO = DEPT_NO;
    }
    
    public String getDept_Name() {
        return Dept_Name;
    }
    
    public void setDept_Name(String Dept_Name) {
        this.Dept_Name = Dept_Name;
    }
    
    public String getLocation() {
        return Location;
    }
    
    public void setLocation(String Location) {
        this.Location = Location;
    }
    
    @Override
    public void add() {
        String insert = "insert into department values ("
                +DEPT_NO  +",'"
                +Dept_Name+"','"
                +Location +"')";
        boolean isAdd = db.go.runNonQuery(insert);
        if (isAdd) {
            Tools.msgBox("Department Added !");
        }
    }
    
    @Override
    public void update() {
        String Update = "update department set DEPT_NAME = '"
                + Dept_Name + "',DEPT_LOCATION='"
                + Location + "'where DEPT_NO =" + DEPT_NO;
        boolean isUpdated = db.go.runNonQuery(Update);
        if (isUpdated) {
            Tools.msgBox("Department Updated !");
        }
        
    }
    
    @Override
    public void delete() {
        String Del = "Delete from department where DEPT_NO=" + DEPT_NO;
        boolean isDeleted = db.go.runNonQuery(Del);
        if (isDeleted) {
            Tools.msgBox("Department Deleted !");
        }
    }
    
    @Override
    public String getAutoNumber() {
        String auto = db.go.getAutoNumber("department", "DEPT_NO");
        return auto;
    }
    
    @Override
    public void getAllRows(JTable table) {
        db.go.fillToJTable("department_data", table);
    }
    
    @Override
    public void getOneRow(JTable table) {
        String row = "select * from department_data where DEPT_NO = " + DEPT_NO;
        db.go.fillToJTable(row, table);
    }
    
    @Override
    public void getCustomRows(String Statement, JTable table) {
     db.go.fillToJTable(Statement, table);
    }
    
    @Override
    public String getValueByName(String Name) {
     String strSelect = "select Dept_No from department where Dept_Name ='" + Name+ "'"; 
     String strValue = (String)db.go.getTableData(strSelect).Items[0][0]; // To get only select row Data not all tabe items
     return strValue;
    }
    
    @Override
    public String getNameByVaue(String Value) {
       String strSelect ="select Dept_Name from department where Dept_NO = '"+Value+"'";
       String strValue = (String) db.go.getTableData(strSelect).Items[0][0];
       return strValue;
               
    }
    
}

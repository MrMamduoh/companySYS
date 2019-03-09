package Entity;

import company.Tools;
import javax.swing.JTable;

public class Department implements MainData {
    
    private int Dept_NO;
    private String Dept_Name;
    private String Location;
    
    public int getDept_NO() {
        return Dept_NO;
    }
    
    public void setDept_NO(int Dept_NO) {
        this.Dept_NO = Dept_NO;
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
                + Dept_NO + ","
                + Dept_Name + ","
                + Location + ")";
        boolean isAdd = db.go.runNonQuery(insert);
        if (isAdd) {
            Tools.msgBox("Department Added !");
        }
    }
    
    @Override
    public void update() {
        String Update = "update department set DEPT_NAME = '"
                + Dept_Name + "',DEPT_LOCATION='"
                + Location + "'where DEPT_NO =" + Dept_NO;
        boolean isUpdated = db.go.runNonQuery(Update);
        if (isUpdated) {
            Tools.msgBox("Department Updated !");
        }
        
    }
    
    @Override
    public void delete() {
        String Del = "Delete from department where DEPT_NO=" + Dept_NO;
        boolean isDeleted = db.go.runNonQuery(Del);
        if (isDeleted) {
            Tools.msgBox("Department Deleted !");
        }
    }
    
    @Override
    public String getAutoNumber() {
    String auto = db.go.getAutoNum("department", "Dept_NO");
    return auto;
    }
    
    @Override
    public void getAllRows(JTable table) {
      
    }
    
    @Override
    public void getOneRow(JTable table) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void getCustomRows(String Statement, JTable table) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String getValueByName(String Name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String getNameByVaue(String Value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

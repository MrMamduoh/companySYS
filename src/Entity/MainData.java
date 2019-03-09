package Entity;

import javax.swing.JTable;

public interface MainData {
    public void add();
    public void update();
    public void delete();
    public String getAutoNumber();
    public void getAllRows(JTable table);
    public void getOneRow(JTable table);
    public void getCustomRows(String Statement, JTable table);
    public String getValueByName(String Name);
    public String getNameByVaue(String Value);
}

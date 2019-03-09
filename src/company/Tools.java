package company;



import java.awt.Component;
import java.awt.Container;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class Tools{

    public static void msgBox(String message){
        JOptionPane.showMessageDialog(null, message);
    }
    
    public static void CreateFolder(String folderName, String path){
        File f = new File(path + "/" + folderName);
        f.mkdir();
    }
    
    public static void CreateFolder(String folderName){
        File f = new File(folderName);
        f.mkdir();        
    }
    
    public static void openForm(JFrame form){            
        try {
            form.setLocationRelativeTo(null);
            Image img = ImageIO.read(Tools.class.getResource("emp.png"));
            form.setIconImage(img);
            form.setDefaultCloseOperation(2);
            form.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void clearText(Container form){
        for(Component c : form.getComponents()){
            if(c instanceof JTextField){
                JTextField txt = (JTextField)c;
                txt.setText("");
            }
            else if(c instanceof Container){
                clearText((Container)c);
            }
        }
    }
    
    public static void createEmptyFile(String fileName){
        try {
            File f = new File(fileName + ".txt");
            f.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void createEmptyFiles(String fileNames[]){
        for(String str : fileNames){
            createEmptyFile(str);
        }
    }
    
    public static void createFile(String fileName, Object data[]){
        try {
            PrintWriter p = new PrintWriter(fileName + ".txt");
            for(Object obj : data){
                p.println(obj);
            }
            p.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public static void createFiles(String fileNames[], Object allData[][]){
        for(int x = 0 ; x < fileNames.length ; x++){
            createFile(fileNames[x], allData[x]);
        }
    }

    public static Object InputBox(String title){
        Object myObj = JOptionPane.showInputDialog(title);
        return myObj;
    }
    
    public static String getNumber(String text){
        String val = "";
        for(char c : text.toCharArray()){
            if(c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9'){
                val += c;
            }            
        }
        return val;
    }
    
    public static int getNumberToInteger(String text){
        String val = "";
        for(char c : text.toCharArray()){
            if(c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9'){
                val += c;
            }            
        }
        return Integer.parseInt(val);
    }
        
    public static String removeNumber(String text){
        String val = "";
        for(char c : text.toCharArray()){
            if(  !(c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9')  ){
                val += c;
            }            
        }
        return val;
    }

    public class Table{
        public int columns;
        public Object[][] Items;
        public Table(int columns){
            this.columns = columns;
            Items = new Object[0][columns];
        }
        public void addNewRow(Object row[]){
            Object TempItems[][] = Items;
            Items = new Object[Items.length +1][columns];
            for(int x = 0; x < TempItems.length; x++){
                Items[x] = TempItems[x];
            }
            Items[Items.length -1] = row;
        }
    }
}
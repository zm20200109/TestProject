/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import model.User;

/**
 *
 * @author Acer
 */
public class ModelabeleUlogovani extends AbstractTableModel{

    private ArrayList<User> ulogovani=new ArrayList<>();

    public ModelabeleUlogovani( ArrayList<User> users) {
    
    ulogovani=users;
    
    }
    
    private String kolone[]={"USERNAME"};
    
    
    
    
    @Override
    public int getRowCount() {
return ulogovani.size();
    }

    @Override
    public int getColumnCount() {

        return kolone.length;

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {


       User u = ulogovani.get(rowIndex);
       
       switch(columnIndex){
           case 0:
               return u.getUsername();
           default:
               return "N/A";
       }






    }

    @Override
    public String getColumnName(int column) {


        return kolone[column];


    }
    
    
    
    
    
    
}

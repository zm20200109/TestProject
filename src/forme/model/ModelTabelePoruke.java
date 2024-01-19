/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Poruka;

/**
 *
 * @author Acer
 */
public class ModelTabelePoruke extends AbstractTableModel{

    
    private List<Poruka> poruke  = new ArrayList<>();

    public ModelTabelePoruke(List<Poruka> poruke) {
    this.poruke=poruke;
    
    }
    
    private String kolone[]={"FROM","TEXT","TO","DATE"};    

    
    
    @Override
    public int getRowCount() {
     return poruke.size();
    }

    @Override
    public int getColumnCount() {

        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {


        Poruka p = poruke.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return p.getPosiljalac().getUsername();
            case 1:
                if(p.getTekst().length()>19)
                return p.getTekst().substring(0, 19)+"...";
                else
                    return p.getTekst();

            case 2:
                return p.getPrimalac().getUsername();
            case 3:
                return p.getDatumVreme();
            default:
                return "N/A";
            
            
            
            
            
        }




    }

    @Override
    public String getColumnName(int column) {

        return kolone[column];
    }

    public List<Poruka> getPoruke() {
        return poruke;
    }

    public void setPoruke(ArrayList<Poruka> poruke) {
        this.poruke = poruke;
    }
    
    
    
    
    
    
    
}

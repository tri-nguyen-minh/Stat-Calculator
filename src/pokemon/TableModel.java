
package pokemon;

import java.util.Vector;
import javax.swing.table.AbstractTableModel;


public class TableModel<E> extends AbstractTableModel{
    String[] header;
    int[] index;
    Vector<Pokemon> data;
    public TableModel(String[] header, int[] index)
    {
        int i = 0;
        this.header= new String[header.length];
        for (i=0;i<header.length;i++) this.header[i]=header[i];
        this.index= new int[index.length];
        for (i=0;i<header.length;i++) this.index[i]=index[i];
        this.data = new Vector<Pokemon>();
    }
    public Vector<Pokemon> getData(){
        return data;
    }
    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }
    @Override
    public String getColumnName(int col) {
        return (col>=0 && col<header.length)?
                header[col]:"";
    }
    
    @Override
    public Object getValueAt(int row, int col) {
        if (row<0 || row>=data.size() || col<0 || col>=header.length)
        return null;
        Pokemon emp = data.get(row);
        switch (index[col])
        {
            case 0: return emp.getStatus();
            case 1: return emp.getName();
            case 2: return emp.getHp();
            case 3: return emp.getAtk();
            case 4: return emp.getDef();
            case 5: return emp.getSatk();
            case 6: return emp.getSdef();
            case 7: return emp.getSpd();
        }
        return null;
    }
    
}

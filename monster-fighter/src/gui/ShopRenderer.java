package gui;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import items.Items;
import monster.Monster;

public class ShopRenderer extends DefaultListCellRenderer{
	
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component cell = null;

        if (value instanceof Monster) {
            Monster monster = (Monster) value;
            String name = monster.shopString();

            cell = super.getListCellRendererComponent(list, 
                name, 
                index, 
                isSelected, 
                cellHasFocus);
        }
        
        if (value instanceof Items) {
            Items item = (Items) value;
            String name = item.shopString();

            cell = super.getListCellRendererComponent(list, 
                name, 
                index, 
                isSelected, 
                cellHasFocus);
        }
        return cell;
    }
}

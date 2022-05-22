package gui;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import monster.Monster;
import items.Items;

/**
 * The Class ShopRenderer.
 * This class is used by JList to only show the name of monster and items in the JList
 */
@SuppressWarnings("serial")
public class NameRenderer extends DefaultListCellRenderer {
	
    /**
     * The list cell renderer component.
     *
     * @param list, the JList
     * @param value, the object, either monster or item
     * @param index, the index in the list
     * @param isSelected, whether the object is selected in the JList
     * @param cellHasFocus, whether the cell has focus in the JList
     * @return the list cell renderer component
     */
    @SuppressWarnings("rawtypes")
	@Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component cell = null;

        if (value instanceof Monster) {
            Monster monster = (Monster) value;
            String name = monster.getMonsterName();

            cell = super.getListCellRendererComponent(list, 
                name, 
                index, 
                isSelected, 
                cellHasFocus);
        }
        
        if (value instanceof Items) {
            Items item = (Items) value;
            String name = item.getItemName();

            cell = super.getListCellRendererComponent(list, 
                name, 
                index, 
                isSelected, 
                cellHasFocus);
        }
        return cell;
    }
}
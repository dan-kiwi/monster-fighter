package gui;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import monster.Monster;

class MyListCellRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component cell = null;

        if (value instanceof Monster) {
            Monster monster = (Monster) value;
            String name = monster.getDisplayName();

            cell = super.getListCellRendererComponent(list, 
                name, // note this...
                index, 
                isSelected, 
                cellHasFocus);

//            if (status > 0) { // or whatever...
//                cell.setBackground(STATUS_ON_COLOR);
//            } else {
//                cell.setBackground(STATUS_OFF_COLOR);
//            }
        }
        return cell;
    }
}
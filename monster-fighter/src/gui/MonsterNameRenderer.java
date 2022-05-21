package gui;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import monster.Monster;

public class MonsterNameRenderer extends DefaultListCellRenderer {

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
        return cell;
    }
}
package gui;

import javax.swing.DefaultListCellRenderer; 
import javax.swing.JList; import java.util.Locale;

import java.awt.Component; 


public class LocaleRenderer extends DefaultListCellRenderer {
 /** Creates a new instance of LocaleRenderer */   
	public LocaleRenderer(){   } 
	
	public Component getListCellRendererComponent(JList list, Object value, 
		int index, boolean isSelected, boolean cellHasFocus) { 
		
		super.getListCellRendererComponent(list, value,index,isSelected,cellHasFocus);  
		Locale l = (Locale)value; 
		setText(l.getDisplayName());  
		return this;   
	} 
}
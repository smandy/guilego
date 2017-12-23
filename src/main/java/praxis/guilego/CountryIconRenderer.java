package praxis.guilego;


import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CountryIconRenderer extends DefaultTableCellRenderer {
	@Override
	public Component getTableCellRendererComponent(final JTable table,
			Object value, boolean isSelected, boolean hasFocus, int row,
			int column) {
		//CountryBean b = (CountryBean) value;
		//SystemState ss = (SystemState) value;
		//Icon icon = iconForState(ss);

        if ( value !=null) {
            ImageIcon icon = (ImageIcon) value;
            setIcon(icon );
            //ImageIcon icon = icon;
            table.setRowHeight(row, icon.getIconHeight() + 2 );
            table.getColumnModel().getColumn(column).setPreferredWidth(icon.getIconWidth()+2 );
        };
		return this;
	}

}

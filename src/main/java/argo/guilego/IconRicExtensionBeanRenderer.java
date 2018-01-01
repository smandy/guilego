package argo.guilego;


import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class IconRicExtensionBeanRenderer extends DefaultTableCellRenderer {
	@Override
	public Component getTableCellRendererComponent(final JTable table,
			Object value, boolean isSelected, boolean hasFocus, int row,
			int column) {
        if ( value !=null) {
            final IconRicExtensionBean irb = (IconRicExtensionBean) value;
            final ImageIcon icon = irb.getImageIcon();
            setIcon( icon );
            table.setRowHeight(row, icon.getIconHeight() + 2 );
            table.getColumnModel().getColumn(column).setPreferredWidth(icon.getIconWidth()+2 );
        };
		return this;
	}

}

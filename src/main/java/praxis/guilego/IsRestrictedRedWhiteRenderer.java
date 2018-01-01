package argo.guilego;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class IsRestrictedRedWhiteRenderer extends ArgoDefaultTableCellRenderer<Boolean>
{
    IsRestrictedRedWhiteRenderer(String name, Boolean def) {
        super(name, def);
    }

    @Override
	public Component getTableCellRendererComponent(JTable table,
			Object value, boolean isSelected, boolean hasFocus, int row,
			int column) {
		JLabel ret = (JLabel) super.getTableCellRendererComponent(table,
				value, isSelected, hasFocus, row, column);
		
		Boolean boolObject = getValue(value);
		
		if ( boolObject.booleanValue() )
		{
			ret.setBackground( Color.RED );
		}
		else
		{
			ret.setBackground(Color.WHITE);
		}
		ret.setText(getText(boolObject));
		return ret;
	}

	@Override
	public String getText(Boolean t) {
		return t.booleanValue() ? "RESTRICTED" : "";
	}

    @Override
    public Boolean getValueImpl(Object o) {
        return (Boolean)o;

    }
}
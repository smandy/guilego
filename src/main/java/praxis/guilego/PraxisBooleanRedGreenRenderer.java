package praxis.guilego;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class PraxisBooleanRedGreenRenderer extends PraxisDefaultTableCellRenderer<Boolean>
{
    final boolean greenValue;

    public PraxisBooleanRedGreenRenderer(String name, Boolean def) {
        super(name, def);
        greenValue = true;
    }

    public PraxisBooleanRedGreenRenderer(String name, Boolean def, boolean gv) {
        super(name, def);
        greenValue = gv;
    }

    @Override
	public Component getTableCellRendererComponent(JTable table,
			Object value, boolean isSelected, boolean hasFocus, int row,
			int column) {
		JLabel ret = (JLabel) super.getTableCellRendererComponent(table,
				value, isSelected, hasFocus, row, column);
		
		Boolean boolObject = getValue(value);
		
		if ( boolObject.booleanValue() == greenValue )
		{
			ret.setBackground( Color.GREEN );
		}
		else
		{
			ret.setBackground(Color.RED);
		}
		ret.setText(getText(boolObject));
		return ret;
	}

	@Override
	public String getText(Boolean t) {
		return t.booleanValue() ? "TRUE" : "FALSE";
	}

    @Override
    public Boolean getValueImpl(Object o) {
        return (Boolean)o;
    }
}
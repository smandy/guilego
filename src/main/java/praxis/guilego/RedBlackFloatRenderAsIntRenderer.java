package praxis.guilego;

import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class RedBlackFloatRenderAsIntRenderer extends
		PraxisDefaultTableCellRenderer<Float> {

	private final DecimalFormat format = new DecimalFormat("###,##0;(###,##0)");

    public RedBlackFloatRenderAsIntRenderer(String name, Float def) {
        super(name, def);
    }

    @Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		JLabel ret = (JLabel) super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);
		int val = getValue(value).intValue();
		String txt = format.format(val);
		ret.setText(txt);
		ret.setHorizontalAlignment(SwingConstants.RIGHT);
		
		if (!isSelected)
		{
			if ( val<0 )
			{
				ret.setForeground(Color.RED);
			}
			else
			{
				ret.setForeground(Color.BLACK);
			}
		}
		
		return ret;
	}

	@Override
	public String getText(Float t) {
		return Float.toString(t);
	}

    @Override
    public Float getValueImpl(Object o) {
        return (Float)o;

    }
}

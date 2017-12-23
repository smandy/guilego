package praxis.guilego;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

@SuppressWarnings("serial")
public class RedBlackDoubleRenderAsIntRenderer extends
		PraxisDefaultTableCellRenderer<Double> {

	private final DecimalFormat format = new DecimalFormat("###,##0;(###,##0)");

    public RedBlackDoubleRenderAsIntRenderer(String name, Double def) {
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
	public String getText(Double t) {
		return Double.toString(t);
	}

    @Override
    public Double getValueImpl(Object o) {
        return (Double)o;

    }
}

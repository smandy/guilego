package praxis.guilego;

import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class RedBlackIntegerRenderer extends
		PraxisDefaultTableCellRenderer<Integer> {

	private final DecimalFormat format = new DecimalFormat("###,##0;(###,##0)");

    public RedBlackIntegerRenderer(String name, Integer def) {
        super(name, def);
    }

    @Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		JLabel ret = (JLabel) super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);
		int orderQty = getValue(value).intValue();
		String txt = format.format(orderQty);
		ret.setText(txt);
		ret.setHorizontalAlignment(SwingConstants.RIGHT);
		
		if (!isSelected)
		{
			if ( orderQty<0 )
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
	public String getText(Integer t) {
		return Integer.toString(t);
	}

    @Override
    public Integer getValueImpl(Object o) {
        return (Integer)o;
    }
}

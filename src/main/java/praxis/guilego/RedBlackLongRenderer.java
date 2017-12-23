package praxis.guilego;

import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class RedBlackLongRenderer extends
		PraxisDefaultTableCellRenderer<Long> {

	private final DecimalFormat format = new DecimalFormat("###,##0;(###,##0)");

    public RedBlackLongRenderer(String name, Long def) {
        super(name, def);
    }

    @Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		JLabel ret = (JLabel) super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);
		long v  = getValue(value).longValue();
		String txt = format.format(v);
		ret.setText(txt);
		ret.setHorizontalAlignment(SwingConstants.RIGHT);
		
		if (!isSelected)
		{
			if ( v<0 )
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
	public String getText(Long t) {
		return Long.toString(t);
	}

    @Override
    public Long getValueImpl(Object o) {
        return (Long)o;
    }
}

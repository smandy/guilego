package argo.guilego;

import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;

public class DollarValueIntRenderer extends
		ArgoDefaultTableCellRenderer<Integer> {

	private final DecimalFormat format = new DecimalFormat("\u0024###,###;(\u0024###,###)");

    public DollarValueIntRenderer(String name, Integer def) {
        super(name, def);
    }

    @Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		JLabel ret = (JLabel) super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);
		int dollarValueOfPosition = getValue(value).intValue();

		if ( !isSelected)
		{
                    if ( dollarValueOfPosition <0 ) {
			ret.setForeground(Color.RED);
                    } else {
                        ret.setForeground( Color.BLACK);
                    }
		}

		String txt = format.format(dollarValueOfPosition);
		ret.setText(txt);
		ret.setHorizontalAlignment(SwingConstants.RIGHT);
		
		
		return ret;
	}

	@Override
	public String getText(Integer t) {
		return Integer.toString(t.intValue() );
	}

    @Override
    public Integer getValueImpl(Object o) {
        return (Integer)o;
    }
}

package praxis.guilego;

import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;

public class DollarValueDoubleRenderer extends
		PraxisDefaultTableCellRenderer<Double> {

	//private final DecimalFormat format = new DecimalFormat("\u0024###,###;(\0024###,###)");
    private final DecimalFormat format = new DecimalFormat("\u0024###,###;(\u0024###,###)");

    public DollarValueDoubleRenderer(String name, Double def) {
        super(name, def);
    }

    @Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		JLabel ret = (JLabel) super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);
		double dollarValueOfPosition = getValue(value).doubleValue();
		if ( dollarValueOfPosition<0 && !isSelected)
		{
			ret.setForeground(Color.RED);
		}

		String txt = format.format(dollarValueOfPosition);
		ret.setText(txt);
		ret.setHorizontalAlignment(SwingConstants.RIGHT);
		
		
		return ret;
	}

	@Override
	public String getText(Double t) {
		return Double.toString(t.doubleValue());
	}

    @Override
    public Double getValueImpl(Object o) {
       return  (Double)o;
    }
}

package praxis.guilego;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class DoublePriceRenderer extends
		PraxisDefaultTableCellRenderer<Double> {

	private final DecimalFormat format = new DecimalFormat("###,##0.0000");

    public DoublePriceRenderer(String name, Double def) {
        super(name, def);
    }

    @Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		JLabel ret = (JLabel) super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);

        Double d = getValue(value);
        if (d==null) {
            ret.setText("BAD!");
        } else {
            double px = d.doubleValue();
            String txt = format.format(px);
            ret.setText(txt);
            ret.setHorizontalAlignment(SwingConstants.RIGHT);

        }


		return ret;
	}

	@Override
	public String getText(Double f) {
		return Double.toString(f);
	}

    @Override
    public Double getValueImpl(Object o) {
        return ((Double)o);
    }
}

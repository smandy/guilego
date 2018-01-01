package argo.guilego;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class DoubleRenderAsInt extends
		ArgoDefaultTableCellRenderer<Double> {

	private final DecimalFormat format = new DecimalFormat("###,##0");

    public DoubleRenderAsInt(String name, Double def) {
        super(name, def);
    }

    @Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		JLabel ret = (JLabel) super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);
		float px = getValue(value).floatValue();
		String txt = format.format(px);
		ret.setText(txt);
		ret.setHorizontalAlignment(SwingConstants.RIGHT);
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

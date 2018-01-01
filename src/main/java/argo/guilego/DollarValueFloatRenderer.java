package argo.guilego;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class DollarValueFloatRenderer extends
		ArgoDefaultTableCellRenderer<Float> {

	private final DecimalFormat format = new DecimalFormat("\u0024###,###;(\u0024###,###)");

    public DollarValueFloatRenderer(String name, Float def) {
        super(name, def);
    }

    @Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		JLabel ret = (JLabel) super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);
		float dollarValueOfPosition = getValue(value).floatValue();
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
	public String getText(Float t) {
		return Float.toString(t.floatValue());
	}

    @Override
    public Float getValueImpl(Object o) {
       return  (Float)o;
    }
}

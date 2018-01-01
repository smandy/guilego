package argo.guilego;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class RightAlignIntegerWithThousandCommas extends
		ArgoDefaultTableCellRenderer<Integer> {

	private final DecimalFormat format = new DecimalFormat("###,##0");

    public RightAlignIntegerWithThousandCommas(String name, Integer def) {
        super(name, def);
    }

    @Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		JLabel ret = (JLabel) super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);
		int qty = getValue(value).intValue();
		String txt = format.format(qty);
		ret.setText(txt);
		ret.setHorizontalAlignment(SwingConstants.RIGHT);
		return ret;
	}



	@Override
	public String getText(Integer f) {
		return Float.toString(f);
	}

    @Override
    public Integer getValueImpl(Object o) {
        return ((Integer)o);
    }
}

package praxis.guilego;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class RightAlignLongWithThousandCommas extends
		PraxisDefaultTableCellRenderer<Long> {

	private final DecimalFormat format = new DecimalFormat("###,##0");

    public RightAlignLongWithThousandCommas(String name, Long def) {
        super(name, def);
    }

    @Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		JLabel ret = (JLabel) super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);
		long qty = getValue(value).longValue();
		String txt = format.format(qty);
		ret.setText(txt);
		ret.setHorizontalAlignment(SwingConstants.RIGHT);
		return ret;
	}



	@Override
	public String getText(Long f) {
		return Long.toString(f);
	}

    @Override
    public Long getValueImpl(Object o) {
        return ((Long)o);
    }
}

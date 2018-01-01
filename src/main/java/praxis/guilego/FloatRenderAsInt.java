package argo.guilego;

import java.awt.Component;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;

public class FloatRenderAsInt extends
		ArgoDefaultTableCellRenderer<Float> {

	private final DecimalFormat format = new DecimalFormat("###,##0");

    public FloatRenderAsInt(String name, Float def) {
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
	public String getText(Float f) {
		return Float.toString(f);
	}

    @Override
    public Float getValueImpl(Object o) {
        return ((Float)o);
    }
}

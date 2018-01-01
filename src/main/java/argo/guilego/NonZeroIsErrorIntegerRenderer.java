package argo.guilego;

import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
    public class NonZeroIsErrorIntegerRenderer extends
                                                   ArgoDefaultTableCellRenderer<Integer> {

    private final DecimalFormat format = new DecimalFormat("###,##0;(###,##0)");

    public NonZeroIsErrorIntegerRenderer(String name, Integer def) {
        super(name, def);
    }

    @Override
	public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
        JLabel ret = (JLabel) super.getTableCellRendererComponent(table, value,
                                                                  isSelected, hasFocus, row, column);
        int qty = getValue(value).intValue();
        String txt = format.format(qty);
        ret.setText(txt);
        ret.setHorizontalAlignment(SwingConstants.RIGHT);
        if (!isSelected)
            {
                if ( qty != 0 )
                    {
                        ret.setBackground(Color.RED);
                        ret.setForeground(Color.WHITE);
                    }
                else {
                        ret.setForeground(Color.BLACK);
                        ret.setBackground(Color.WHITE);
                };
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

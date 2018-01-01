package argo.guilego;

import javax.swing.*;
import java.awt.*;

public class CenterTextRenderer extends ArgoDefaultTableCellRenderer<String> {


    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel ret = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);    //To change body of overridden methods use File | Settings | File Templates.
        ret.setHorizontalAlignment(SwingConstants.CENTER);
        return ret;
    }

    @Override
    public String getText(String s) {
        return s;
    }

    @Override
    public String getValueImpl(Object o) {
        return (String)o;
    }
}

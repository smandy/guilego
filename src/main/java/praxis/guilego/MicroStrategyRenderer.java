package argo.guilego;

import argo.guilego.ArgoDefaultTableCellRenderer;

import javax.swing.*;
import java.awt.*;

public class MicroStrategyRenderer extends ArgoDefaultTableCellRenderer<String> {
    static final Color EU01_COLOUR = new Color( 255, 120,120 );
    static final Color EU02_COLOUR = new Color( 120, 255, 120);
    static final Color EU03_COLOUR = new Color( 120, 120, 255);
    static final Color EU04_COLOUR = new Color( 255,120,255);
    static final Color EU05_COLOUR = new Color( 120,255,255);
    static final Color EU06_COLOUR = new Color( 255,255,120);

    static final String EU01 = "EU01";
    static final String EU02 = "EU02";
    static final String EU03 = "EU03";
    static final String EU04 = "EU04";
    static final String EU05 = "EU05";
    static final String EU06 = "EU06";

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel ret = (JLabel) super.getTableCellRendererComponent(table, value,
                isSelected, hasFocus, row, column);

        String micro = (String) value;
        ret.setHorizontalAlignment(SwingConstants.CENTER);
        setText(micro);
        if (!isSelected) {
            Color bgCol = Color.WHITE;
            if (micro.equals(EU01)) bgCol = EU01_COLOUR;
            else if ( micro.equals(EU02)) bgCol= EU02_COLOUR;
            else if ( micro.equals(EU03)) bgCol = EU03_COLOUR;
            else if ( micro.equals(EU04)) bgCol = EU04_COLOUR;
            else if ( micro.equals(EU05)) bgCol = EU05_COLOUR;
            else if ( micro.equals(EU06)) bgCol = EU06_COLOUR;
            ret.setBackground(bgCol);
        }
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

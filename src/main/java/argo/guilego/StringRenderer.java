package argo.guilego;

public class StringRenderer extends ArgoDefaultTableCellRenderer<String> {
    @Override
    public String getText(String s) {
        return s;
    }

    @Override
    public String getValueImpl(Object o) {
        return (String)o;
    }
}

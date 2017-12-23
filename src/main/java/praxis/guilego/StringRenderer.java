package praxis.guilego;

public class StringRenderer extends PraxisDefaultTableCellRenderer<String> {
    @Override
    public String getText(String s) {
        return s;
    }

    @Override
    public String getValueImpl(Object o) {
        return (String)o;
    }
}

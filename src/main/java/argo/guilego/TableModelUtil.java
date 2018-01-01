package argo.guilego;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.gui.TableFormat;
import ca.odell.glazedlists.swing.EventTableModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TableModelUtil {
     private static final Log log = LogFactory.getLog(TableModelUtil.class.getName());

	public static <T> void configureJTable(JTable x, ColumnInfo[] columns, EventList<T> filteredList) {
		final String[] columnProperties = new String[columns.length];
		final String[] columnNames      = new String[columns.length];
		
		for (int i = 0; i < columns.length; i++) {
			columnProperties[i] = columns[i].getBeanProperty();
			columnNames[i] = columns[i].getColumnName();
		}
		
		TableFormat<T> tableFormat = GlazedLists.tableFormat(columnProperties, columnNames);

		final EventTableModel<T> myModel = new EventTableModel<T>(filteredList, tableFormat) {
            @Override
            public Object getValueAt(int row, int column) {
                try {
                    return super.getValueAt(row, column);    //To change body of overridden methods use File | Settings | File Templates.
                } catch (NullPointerException ex) {
                    if ( log.isInfoEnabled() ) {log.info("NPE Getting column property " + columnProperties[column] + " FIX ME!!!"); };
                    return null;
                }
            }
        };
		x.setModel(myModel);
		
		for( int i=0;i<columns.length;i++)
		{
			TableCellRenderer renderer = columns[i].getRenderer();
			if ( renderer!=null)
			{
				x.getColumnModel().getColumn(i).setCellRenderer(renderer);
			}
		}
		
		
	}

}


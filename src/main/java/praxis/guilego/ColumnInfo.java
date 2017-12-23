package praxis.guilego;

import javax.swing.table.TableCellRenderer;

public class ColumnInfo {

	private String beanProperty;
	private String columnName;
	private TableCellRenderer renderer;

	public ColumnInfo(String bp, String displayName, TableCellRenderer renderer ) {
		this.beanProperty = bp;
		this.columnName   = displayName;
		this.renderer     = renderer;
	}

	public String getBeanProperty() {
		return beanProperty;
	}

	public String getColumnName() {
		return columnName;
	}

	public TableCellRenderer getRenderer() {
		return renderer;
	}

}

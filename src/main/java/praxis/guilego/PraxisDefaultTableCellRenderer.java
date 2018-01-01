package argo.guilego;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.table.DefaultTableCellRenderer;


/**
 * This is a tagging interface we should use for all our cell renderers.
 * 
 * In cases where a custom renderer is used ( flashing stuff, bid ask etc). Then
 * the 'toString' method of the object will be used which isn't ideal since it's the tex
 * value of the object. This works as a tagging interface in conjunction with the Excel 
 * Adapter so that cut and past works properly.
 * 
 * @author ansmith
 *
 * @param <T>
 * 
 * @see ExcelAdapter
 * 
 * 
 */
public abstract class ArgoDefaultTableCellRenderer<T> extends
		DefaultTableCellRenderer {

    private final String name;
    private final T def;

    protected ArgoDefaultTableCellRenderer(String name, T def) {
        this.name = name;
        this.def = def;
    }

    protected ArgoDefaultTableCellRenderer() {
        this.name = "";
        this.def = null;
    }

    private static final Log log = LogFactory.getLog(ArgoDefaultTableCellRenderer.class.getName());

    boolean errorFlagged = false;

	public abstract String getText(T t);

    public abstract T getValueImpl( Object o );

    public T getValue( Object o ) {
        try {
            return getValueImpl(o);
        } catch (Exception e) {
            if (!errorFlagged) {
                if ( log.isInfoEnabled() ) {
                    log.info("Error extracting field " + name + " " + e);
                }
                errorFlagged = true;
            }
            return def;
        }
    };
}

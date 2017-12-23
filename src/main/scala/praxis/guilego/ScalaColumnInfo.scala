package praxis.guilego

import javax.swing.table.TableCellRenderer
import javax.swing.{JTable, JLabel}
import _root_.ca.odell.glazedlists.{GlazedLists, EventList}
import _root_.ca.odell.glazedlists.gui.TableFormat
import _root_.ca.odell.glazedlists.swing.EventTableModel
import org.apache.commons.logging.{Log, LogFactory}
import praxis.HasJsonObject


// Parameterise on beanttype
case class ScalaColumnInfo(fieldName       : String,
                           label           : String,
                           align          : Option[Int] = None,
                           minWidth       : Option[Int] = None,
                           maxWidth       : Option[Int] = None,
                           preferredWidth : Option[Int] = None,
                           renderer       : Option[TableCellRenderer] = None,
                           fetcher        : Option[(HasJsonObject) => Comparable[_ <: AnyRef]] = None
                            ) {
  def applyTo( jl : JLabel) = {
    align.foreach( jl.setHorizontalAlignment)
  }
}

object ScalaTableModelUtil {
  def configureJTable[T](x: JTable, columns: Array[ScalaColumnInfo], filteredList: EventList[T]) {
    val columnProperties = columns.map( _.fieldName)
    val columnNames      = columns.map( _.label)

    val tableFormat: TableFormat[T] = GlazedLists.tableFormat(columnProperties, columnNames)
    val myModel: EventTableModel[T] = new EventTableModel[T](filteredList, tableFormat) {
      override def getValueAt(row: Int, column: Int): AnyRef = {
        try {
          return super.getValueAt(row, column)
        }
        catch {
          case ex: NullPointerException => {
            if (log.isInfoEnabled) {
              log.info("NPE Getting column property " + columnProperties(column) + " FIX ME!!!")
            }

            return null
          }
        }
      }
    }
    x.setModel(myModel)

    for {
      (ci, i) <- columns.zipWithIndex
      column = x.getColumnModel().getColumn(i)
    } {
      ci.renderer.foreach( column.setCellRenderer )
      ci.preferredWidth.foreach( column.setPreferredWidth)
      ci.minWidth.foreach(column.setMinWidth)
      ci.maxWidth.foreach(column.setMaxWidth)
    }
  }

  private final val log: Log = LogFactory.getLog(ScalaTableModelUtil.getClass.getName)
}




package praxis.guilego

import javax.swing.{JTable, JLabel}
import java.util.{Map => JMap}

import scala.collection.JavaConversions._

class MapExtractorRenderer(var field: String) extends PraxisDefaultTableCellRenderer[JMap[String, String]]("map entry " + field, Map[String,String]()) {
  override def getTableCellRendererComponent(table: JTable, value: Any, isSelected: Boolean, hasFocus: Boolean, row: Int, column: Int) = {
    val ret = super.getTableCellRendererComponent(table, value,
                                                  isSelected, hasFocus, row, column).asInstanceOf[JLabel]
    val jm = value.asInstanceOf[JMap[String,String]]
    ret.setText(getText(jm))
    ret
  }

  def getText(jm: JMap[String, String]) = {
    val txt = jm.get(field);
    if (txt == null) "UNMAPPED" else txt
  }

  def getValueImpl(o: Object) : JMap[String,String] = o.asInstanceOf[JMap[String,String]]
}


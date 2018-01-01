package argo.guilego

import java.util.{Map => JMap}
import javax.swing.{JLabel, JTable}
import java.awt.Color

import scala.collection.JavaConversions._

class RedGreenBooleanMapExtractorRenderer(var field: String) extends ArgoDefaultTableCellRenderer[JMap[String, String]]("field=%s".format(field), Map[String,String]()) {
  override def getTableCellRendererComponent(table: JTable, value: Any, isSelected: Boolean, hasFocus: Boolean, row: Int, column: Int) = {
    val ret = super.getTableCellRendererComponent(table, value,
                                                  isSelected, hasFocus, row, column).asInstanceOf[JLabel]
    val jm: JMap[String, String] = value.asInstanceOf[JMap[String, String]]
    val optText = jm.get(field)
    val optBool = getBool(jm)
    val txt = getTextForBool(optBool)

    optBool match {
      case Some(true) => ret.setBackground(Color.GREEN)
      case Some(false) => ret.setBackground(Color.RED)
      case None => ret.setBackground(Color.WHITE)
    }
    ret.setText(txt)
    ret
  }

  def getBool(jm: JMap[String, String]): Option[Boolean] = {
    val optText = jm.get(field)
    if (optText == null) {
      None
    } else {
      val b = java.lang.Boolean.parseBoolean(optText)
      Some(b)
    }
  }

  def getTextForBool(optBool: Option[Boolean]) = optBool match {
    case Some(b) => b.toString
    case None => "UNDEFINED"
  }

  def getText(jm: JMap[String, String]) = getTextForBool(getBool(jm))



  def getValueImpl(o: Any) = o.asInstanceOf[JMap[String,String]]
}

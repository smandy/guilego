package argo.algo.guiLego

import javax.swing.{JLabel, JTable}

import java.util.{Map => JMap, HashMap => JHashMap}
import argo.guilego.ArgoDefaultTableCellRenderer


class FloatMapExtractorRenderer(var field: String) extends ArgoDefaultTableCellRenderer[JMap[String, Float]]( "map entry " + field, new JHashMap[String,Float]()) {
  override def getTableCellRendererComponent(table: JTable, value: Any, isSelected: Boolean, hasFocus: Boolean, row: Int, column: Int) = {
    val ret = super.getTableCellRendererComponent(table, value,
                                                  isSelected, hasFocus, row, column).asInstanceOf[JLabel]
    val jm : JMap[String,Float]  = value.asInstanceOf[JMap[String,Float]]
    ret.setText(getText(jm))
    ret
  }

  def getText(jm: JMap[String, Float]) = {
    //log.info("Jm is " + jm + " field is " + field + " " + jm.containsKey(field) + " " + jm.get(field))
    //val ret = 
    if (jm.containsKey(field)) {
      jm.get(field).toString
    } else {
      "UNMAPPED"
    }
    /*      log.info("Returning " + ret )
     ret*/
  }

  def getValueImpl(o: Object) : JMap[String,Float] = o.asInstanceOf[JMap[String,Float]]

}

package praxis.commandControl

import java.awt.{Color, Component}
import javax.json.JsonObject
import javax.swing.{JLabel, JTable}

import org.apache.commons.logging.LogFactory
import praxis.guilego.{ScalaColumnInfo, PraxisDefaultTableCellRenderer}

import scala.annotation.tailrec


object MultiChoiceRenderer {
  val log = LogFactory.getLog( MultiChoiceRenderer.getClass.getName)
}

case class MultiChoiceRenderer(sci : ScalaColumnInfo, json : JsonObject) extends PraxisDefaultTableCellRenderer[AnyRef] {
  import MultiChoiceRenderer._

  type ChoiceType = (String,Option[Color],Option[Color])

  val choices = {
    val ary = json.getJsonArray("choices")
    val sz = ary.size()

    @tailrec
    def loop( idx : Int = 0, prev : List[ChoiceType] = Nil) : List[ChoiceType] = {
      if (idx == sz) {
        prev
      } else {
        val subObj = ary.getJsonObject(idx)
        loop( idx + 1 , ( subObj.getString("value") ,
          Option(subObj.getString("bgCol", null)).flatMap( x => ColumnModelParser.colorForString.get(x) ),
          Option(subObj.getString("fgCol", null)).flatMap( x => ColumnModelParser.colorForString.get(x)  ) )  :: prev )
      }
    }

    val ret = loop()
    log.info("Parsed " + ret)
    ret
  }

  override def getTableCellRendererComponent(table: JTable, value: AnyRef, isSelected: Boolean, hasFocus: Boolean, row: Int, column: Int) = {
    val ret = super.getTableCellRendererComponent(table, value,
      isSelected, hasFocus, row, column).asInstanceOf[JLabel]
    sci.applyTo(ret)
    val v = value.toString

    @tailrec
    def tryToApplyColorTo( xs : List[ChoiceType] ) : Unit = xs match {
      case (choice, bg, fg) :: xs => {
        if (choice==v) {
          bg foreach ret.setBackground
          fg foreach ret.setForeground
        }
        tryToApplyColorTo(xs)
      }
      case Nil => ()
    }
    tryToApplyColorTo(choices)
    ret
  }
  log.info("Have " + json)

  override def getText(t: scala.AnyRef): String = "FIXME"

  override def getValueImpl(o: scala.AnyRef): AnyRef = "FIXME"
}

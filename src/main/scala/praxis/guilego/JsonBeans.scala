package argo.guilego

import java.awt.{Color, Component, Graphics}
import javax.json.JsonObject
import javax.swing.table.DefaultTableCellRenderer
import javax.swing.{JLabel, JTable}

import org.apache.commons.logging.LogFactory

object RenderLego {
  val defaultStringRender = (jl: JLabel, s: String) => jl.setText(s)

  val defaultBoolRender = (jl: JLabel, b: Boolean, isSelected: Boolean) => {
    val ret = if (b) "TRUE" else "FALSE"
    jl.setText(ret)
  }
  val defaultIntRender = (jl: JLabel, isSelected: Boolean, v: Int) => jl.setText(v.toString)

  val defaultDoubleRender = (jl: JLabel, isSelected: Boolean, v: Double) => jl.setText(v.toString)
}

class JsonStringRenderer(sci: ScalaColumnInfo,
                         render: (JLabel, String) => Unit = RenderLego.defaultStringRender) extends ArgoDefaultTableCellRenderer[String] {
  override def getTableCellRendererComponent(table: JTable, value: scala.Any, isSelected: Boolean, hasFocus: Boolean, row: Int, column: Int): Component = {
    val ret = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column).asInstanceOf[JLabel]
    val s = getValue(value)
    sci.applyTo(ret)
    render(ret, s) // unto caesar what is caesars
    ret
  }

  def getText(t: String): String = t

  def getValueImpl(o: scala.Any): String = o.asInstanceOf[String]
}

class MaybeAlignRenderer(sci: ScalaColumnInfo) extends DefaultTableCellRenderer {
  override def getTableCellRendererComponent(table: JTable, value: scala.Any, isSelected: Boolean, hasFocus: Boolean, row: Int, column: Int): Component = {
    val ret = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column).asInstanceOf[JLabel]
    sci.applyTo(ret)
    ret
  }
}

class JsonBooleanRenderer(sci: ScalaColumnInfo,
                          render: (JLabel, Boolean, Boolean) => Unit = RenderLego.defaultBoolRender) extends
ArgoDefaultTableCellRenderer[Boolean] {
  override def getTableCellRendererComponent(table: JTable, value: scala.Any, isSelected: Boolean, hasFocus: Boolean, row: Int, column: Int): Component = {
    val ret = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column).asInstanceOf[JLabel]
    val s = getValue(value)
    sci.applyTo(ret)
    render(ret, s, isSelected) // unto caesar what is caesars
    ret
  }

  def getText(b: Boolean): String = b.toString()

  def getValueImpl(o: scala.Any): Boolean = o.asInstanceOf[Boolean]
}

class JsonIntRenderer(sci: ScalaColumnInfo,
                      render: (JLabel, Boolean, Int) => Unit = RenderLego.defaultIntRender) extends
ArgoDefaultTableCellRenderer[Int] {
  override def getTableCellRendererComponent(table: JTable, value: scala.Any, isSelected: Boolean, hasFocus: Boolean, row: Int, column: Int): Component = {
    val ret = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column).asInstanceOf[JLabel]
    val s = getValue(value)
    sci.applyTo(ret)
    render(ret, isSelected, s) // unto caesar what is caesars
    ret
  }

  def getText(b: Int): String = b.toString()

  def getValueImpl(o: scala.Any): Int = o.asInstanceOf[Int]
}

object BidAskRenderer {
  val log = LogFactory.getLog(BidAskRenderer.getClass.getName)
}

class BidAskRenderer(sci: ScalaColumnInfo, obj: JsonObject) extends ArgoDefaultTableCellRenderer[JsonObject] {

  def safeGet(s: String) = try {
    obj.getString(s)
  } catch {
    case npe: NullPointerException => throw new IllegalStateException(s"Missing json field for bidAskRenderer $s")
  }

  val bidField = safeGet("bidField")
  val askField = safeGet("askField")
  val fvField = safeGet("fvField")
  val xclBidField = safeGet("xclBidField")
  val xclAskField = safeGet("xclAskField")

  val renderMinField = safeGet("renderMinField")
  val renderMaxField = safeGet("renderMaxField")

  override def getTableCellRendererComponent(table: JTable, value: scala.Any,
                                             isSelected: Boolean, hasFocus: Boolean,
                                             row: Int, column: Int): Component = {
    val ret = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column).asInstanceOf[JLabel]
    val s = getValue(value)
    optJson = Some(s)
    repaint
    ret
  }

  var optJson: Option[JsonObject] = None
  var badFields = Set[String]()

  override def paint(g: Graphics): Unit = {
    for {
      obj <- optJson
    } {
      var allOkay = true
      def getDouble(s: String) = obj.getJsonNumber(s).doubleValue()
      try {
        val renderMin = getDouble(renderMinField)
        val renderMax = getDouble(renderMaxField)

        val bid = getDouble(bidField)
        val ask = getDouble(askField)

        val xclBid = getDouble( xclBidField)
        val xclAsk = getDouble( xclAskField)

        val fv = getDouble(fvField)

        val height = getHeight
        val width = getWidth
        val scaleX = width / (renderMax - renderMin)

        def priceToX(d: Double): Int = ((d - renderMin) * scaleX).toInt
        def draw(px: Double) = g.fillRect(priceToX(px), 0, 2, height)

        g.setColor(Color.GREEN)

        val bidX = priceToX(bid)
        val askX = priceToX(ask)
        val spreadX = askX - bidX
        g.fillRect( bidX, 0, spreadX, height)

        draw(bid)
        draw(ask)

        g.setColor(Color.BLUE)
        draw(fv)

        g.setColor(Color.RED)
        draw(xclAsk)
        draw(xclBid)

      } catch {
        case npe : NullPointerException => {
          // pass
        }
      }
    }
  }

  def getText(ks: JsonObject): String = "Json!"

  def getValueImpl(o: scala.Any): JsonObject = o.asInstanceOf[JsonObject]
}


class JsonDoubleRenderer(sci: ScalaColumnInfo,
                         render: (JLabel, Boolean, Double) => Unit = RenderLego.defaultDoubleRender) extends
ArgoDefaultTableCellRenderer[Double] {
  override def getTableCellRendererComponent(table: JTable, value: scala.Any,
                                             isSelected: Boolean, hasFocus: Boolean,
                                             row: Int, column: Int): Component = {
    val ret = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column).asInstanceOf[JLabel]
    val s = getValue(value)
    sci.applyTo(ret)
    render(ret, isSelected, s) // unto caesar what is caesars
    ret
  }

  def getText(b: Double): String = b.toString()

  def getValueImpl(o: scala.Any): Double = o.asInstanceOf[Double]
}




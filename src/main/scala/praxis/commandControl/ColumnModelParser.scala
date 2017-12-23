package praxis.commandControl

import java.awt.Color
import java.text.DecimalFormat
import javax.json.{JsonArray, JsonObject}
import javax.swing.{JLabel, SwingConstants}

import org.apache.commons.logging.LogFactory
import praxis.HasJsonObject
import praxis.PraxisJsonUtil.jsonFromStream
import praxis.guilego._

import scala.reflect.ClassTag
import scala.util.Try

object ColumnModelParser {
  val log = LogFactory.getLog(ColumnModelParser.getClass.getName())


  //val cc = new ColumnContext(null)

  def main(args: Array[String]): Unit = {
    // TODO - now expects to bas pssed fields not an inputstrema
/*
    val ret = parse[InstrumentBean](new ColumnContext(null),
      jsonFromStream(ColumnModelParser.getClass.getClassLoader.getResourceAsStream("columnModel.js")).getJsonArray("fields")
    )
    log.info(s"ret is $ret")
*/
  }

  def parse[T](implicit cc: ColumnContext, fields: JsonArray): Array[ScalaColumnInfo] = {
    cc.reset
    val ret = new java.util.ArrayList[ScalaColumnInfo]()
    val jsonColumns = new java.util.ArrayList[ScalaColumnInfo]()
    var idx = 0
    while (idx < fields.size) {
      val obj = fields.getJsonObject(idx)
      //log.info(s"$idx -> $obj")
      if (obj.size < 2) throw new IllegalArgumentException("Must have at least two columns!")
      val disabled = obj.getBoolean("disabled", false).booleanValue()
      if (!disabled) {
        // We need more info to create renderer so we'll calculate everything else then 'mutate' our
        // renderer on with a cheeky copy operation. Big ugly but we'll get an immutable object out of it.
        val thaip = obj.getString("type")
        val firstPass = ScalaColumnInfo(
          obj.getString("fieldName"),
          obj.getString("label"),
          align = Try(obj.getString("align")).toOption.map(alignmentForString),
          minWidth = Try(obj.getInt("minWidth")).toOption,
          maxWidth = Try(obj.getInt("maxWidth")).toOption,
          preferredWidth = Try(obj.getInt("preferredWidth")).toOption)

        val (isJson, ci) = thaip match {
          case "jsonString"       => (true, addJsonStringRenderer(firstPass, obj))
          case "jsonInt"          => (true, addJsonIntRenderer(firstPass, obj))
          case "jsonBool"         => (true, addJsonBoolRenderer(firstPass, obj))
          case "jsonDouble"       => (true, addJsonDoubleRenderer(firstPass, obj))
          case "bidAsk"           => (false, makeBidAskRenderer(firstPass, obj))
          case "vanillaMicroStrategy"    => (false, firstPass.copy(renderer = Some( new MicroStrategyRenderer()) ) )
          case "vanillaMultiChoice"      => (false, firstPass.copy(renderer = Some( MultiChoiceRenderer(firstPass, obj)) ) )
          case "vanillaString"           => (false, firstPass.copy(renderer = Some(new JsonStringRenderer(firstPass))))
          case "vanillaBool"      => (false, firstPass.copy(renderer = Some(makeBoolRenderer(firstPass, obj))))
          case "vanillaInt"       => (false, firstPass.copy(renderer = Some(makeIntRenderer(firstPass, obj))))
          case "vanillaDouble"    => (false, firstPass.copy(renderer = Some(makeDoubleRenderer(firstPass, obj))))
          case "vanillaField"     => (false, firstPass.copy(renderer = Some(new MaybeAlignRenderer(firstPass)))) // The default
        }
        if (isJson) {
          jsonColumns.add(ci)
        }
        ret.add(ci)
      }
      idx += 1
    }
    //log.info(s"Have $obj")

    implicit val im = implicitly[ClassTag[ScalaColumnInfo]]
    cc.columns = jsonColumns.toArray(Array.empty[ScalaColumnInfo])
    ret.toArray(Array.empty[ScalaColumnInfo])
  }

  def addJsonIntRenderer(sci: ScalaColumnInfo, obj: JsonObject)(implicit cc: ColumnContext) = {
    sci.copy(
      // Utter puke
      fetcher = Some((ib: HasJsonObject) => new java.lang.Integer(ib.json.getInt(sci.fieldName))),
      fieldName = cc.next,
      renderer = Some(makeIntRenderer(sci, obj))
    )
  }

  def makeBidAskRenderer( sci : ScalaColumnInfo, obj : JsonObject) = {
    sci.copy( fieldName = "json",renderer = Some( new BidAskRenderer(sci, obj)) )
  }

  def addJsonDoubleRenderer(sci: ScalaColumnInfo, obj: JsonObject)(implicit cc: ColumnContext) = {
    sci.copy(
      fetcher = Some(
        (ib: HasJsonObject) => {
          val ret: Double = Try(ib.json.getJsonNumber(sci.fieldName).doubleValue).getOrElse(Double.NaN)
          new java.lang.Double(ret)
        }
      ),
      fieldName = cc.next,
      renderer = Some( makeDoubleRenderer(sci, obj))
    )
  }


  def addJsonStringRenderer(sci: ScalaColumnInfo, obj: JsonObject)(implicit cc: ColumnContext) = {
    sci.copy(
      fieldName = cc.next,
      fetcher = Some((ib: HasJsonObject) => ib.json.getString(sci.fieldName)),
      renderer = Some(new JsonStringRenderer(sci))
    )
  }

  def makeDoubleRenderer(sci : ScalaColumnInfo, obj : JsonObject) = {
    val dd = obj.getJsonObject("doubleData")
    if (dd != null) {
      val format = new DecimalFormat(dd.getString("format"))
      val negativeRed = dd.getBoolean("negativeRed", false)
      val render = (jl: JLabel, isSelected: Boolean, d: Double) => {
        jl.setText(format.format(d))
        if (!isSelected) {
          if (negativeRed && d < 0) {
            jl.setForeground(Color.RED)
          } else {
            jl.setForeground(Color.BLACK)
          }
        }
      }
      new JsonDoubleRenderer(sci, render)
    } else {
      new JsonDoubleRenderer(sci)
    }
  }

  def makeIntRenderer(sci: ScalaColumnInfo, obj: JsonObject) = {
    val intData = obj.getJsonObject("intData")
    if (intData != null) {
      val formatter: (Int) => String = try {
        val df = new DecimalFormat(intData.getString("format"))
        (d: Int) => df.format(d)
      } catch {
        case npe: NullPointerException => (d: Int) => d.toString
      }
      val nonZero: Option[(Color, Color)] = {
        Option(intData.getJsonObject("nonZero")) map {
          x => {
            val fg = colorForString(x.getString("foreground"))
            val bg = colorForString(x.getString("background"))
            (fg, bg)
          }
        }
      }
      //log.info("NonZero is " + nonZero)
      val negativeRed = intData.getBoolean("negativeRed", false)
      val newRenderer = (jl: JLabel, isSelected: Boolean, d: Int) => {
        jl.setText(formatter(d))
        (isSelected, nonZero) match {
          case (false, Some((fg, bg))) if (d != 0) => {
            // Our very special case
            jl.setForeground(fg)
            jl.setBackground(bg)
          }
          case (false, _) if (negativeRed && d < 0) => {
            // Negative number we paint red?
            jl.setForeground(Color.RED)
            jl.setBackground(Color.WHITE)
          }
          case (false, _) => {
            jl.setForeground(Color.BLACK) // Everythign else paint black rolling stones style
            jl.setBackground(Color.WHITE)
          }
          case (true, _) => {
            //jl.setForeground(Color.BLACK)
            //jl.setBackground(Color.WHITE)
          } // If selected we don't muck with it
        }
      }
      new JsonIntRenderer(sci, newRenderer)
    } else {
      new JsonIntRenderer(sci)
    }
  }




  def makeBoolRenderer(sci: ScalaColumnInfo, obj: JsonObject): JsonBooleanRenderer = {
    val bd = obj.getJsonObject("boolData")
    if (bd != null) {
      val trueData = BoolRenderData.forObject(bd.getJsonObject("true"))
      val falseData = BoolRenderData.forObject(bd.getJsonObject("false"))
      val render = (jl: JLabel, b: Boolean, isSelected: Boolean) => {
        val BoolRenderData(label, bg, fg) = if (b) trueData else falseData
        if (!isSelected) {
          jl.setBackground(bg)
          jl.setForeground(fg)
        }
        jl.setText(label)
      }
      new JsonBooleanRenderer(sci, render)
    } else {
      new JsonBooleanRenderer(sci)
    }
  }

  def addJsonBoolRenderer(sci: ScalaColumnInfo, obj: JsonObject)(implicit cc: ColumnContext): ScalaColumnInfo = {
    sci.copy(
      fetcher = Some(
        (ib: HasJsonObject) => {
          def choke(): java.lang.Boolean = {
            if (!badFields.contains(sci.fieldName)) {
              log.info(s"FIXME : Field ${sci.fieldName} is broken!")
              badFields += sci.fieldName
            }
            java.lang.Boolean.FALSE
          }
          try {
            new java.lang.Boolean(ib.json.getBoolean(sci.fieldName))
          } catch {
            case npe: NullPointerException => choke()
            case cce: ClassCastException => choke()
          }
        }),
      fieldName = cc.next,
      renderer = Some(makeBoolRenderer(sci, obj)))
  }

  var badFields = Set[String]()


  val alignmentForString = Map("CENTER" -> SwingConstants.CENTER,
    "LEFT" -> SwingConstants.LEFT,
    "RIGHT" -> SwingConstants.RIGHT)


  object BoolRenderData {
    def forObject(js: JsonObject) = BoolRenderData(
      js.getString("label"),
      colorForString(js.getString("bg")),
      colorForString(js.getString("fg")))
  }

  case class BoolRenderData(label: String, bgCol: Color, fgCol: Color)


  val colorForString = Map("white" -> Color.white,
    "WHITE" -> Color.WHITE,
    "lightGray" -> Color.lightGray,
    "gray" -> Color.gray,
    "GRAY" -> Color.GRAY,
    "darkGray" -> Color.darkGray,
    "black" -> Color.black,
    "BLACK" -> Color.BLACK,
    "red" -> Color.red,
    "RED" -> Color.RED,
    "pink" -> Color.pink,
    "PINK" -> Color.PINK,
    "orange" -> Color.orange,
    "ORANGE" -> Color.ORANGE,
    "yellow" -> Color.yellow,
    "YELLOW" -> Color.YELLOW,
    "green" -> Color.green,
    "GREEN" -> Color.GREEN,
    "magenta" -> Color.magenta,
    "MAGENTA" -> Color.MAGENTA,
    "cyan" -> Color.cyan,
    "CYAN" -> Color.CYAN,
    "blue" -> Color.blue,
    "BLUE" -> Color.BLUE)


}

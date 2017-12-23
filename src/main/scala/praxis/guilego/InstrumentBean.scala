package praxis.guilego

import praxis.HasJsonObject
import scala.beans.BeanProperty
import javax.json.JsonObject


trait JsonColumns { self : HasJsonObject =>
  def context : ColumnContext

  //def json : JsonObject

  def getColumn0: Comparable[_ <: AnyRef] = context.columns(0).fetcher.get.apply(this)

  def getColumn1: Comparable[_ <: AnyRef] = context.columns(1).fetcher.get.apply(this)

  def getColumn2: Comparable[_ <: AnyRef] = context.columns(2).fetcher.get.apply(this)

  def getColumn3: Comparable[_ <: AnyRef] = context.columns(3).fetcher.get.apply(this)

  def getColumn4: Comparable[_ <: AnyRef] = context.columns(4).fetcher.get.apply(this)

  def getColumn5: Comparable[_ <: AnyRef] = context.columns(5).fetcher.get.apply(this)

  def getColumn6: Comparable[_ <: AnyRef] = context.columns(6).fetcher.get.apply(this)

  def getColumn7: Comparable[_ <: AnyRef] = context.columns(7).fetcher.get.apply(this)

  def getColumn8: Comparable[_ <: AnyRef] = context.columns(8).fetcher.get.apply(this)

  def getColumn9: Comparable[_ <: AnyRef] = context.columns(9).fetcher.get.apply(this)

  def getColumn10: Comparable[_ <: AnyRef] = context.columns(10).fetcher.get.apply(this)

  def getColumn11: Comparable[_ <: AnyRef] = context.columns(11).fetcher.get.apply(this)

  def getColumn12: Comparable[_ <: AnyRef] = context.columns(12).fetcher.get.apply(this)

  def getColumn13: Comparable[_ <: AnyRef] = context.columns(13).fetcher.get.apply(this)

  def getColumn14: Comparable[_ <: AnyRef] = context.columns(14).fetcher.get.apply(this)

  def getColumn15: Comparable[_ <: AnyRef] = context.columns(15).fetcher.get.apply(this)

  def getColumn16: Comparable[_ <: AnyRef] = context.columns(16).fetcher.get.apply(this)

  def getColumn17: Comparable[_ <: AnyRef] = context.columns(17).fetcher.get.apply(this)

  def getColumn18: Comparable[_ <: AnyRef] = context.columns(18).fetcher.get.apply(this)

  def getColumn19: Comparable[_ <: AnyRef] = context.columns(19).fetcher.get.apply(this)

  def getColumn20: Comparable[_ <: AnyRef] = context.columns(20).fetcher.get.apply(this)
  def getColumn21: Comparable[_ <: AnyRef] = context.columns(21).fetcher.get.apply(this)
  def getColumn22: Comparable[_ <: AnyRef] = context.columns(22).fetcher.get.apply(this)
  def getColumn23: Comparable[_ <: AnyRef] = context.columns(23).fetcher.get.apply(this)
  def getColumn24: Comparable[_ <: AnyRef] = context.columns(24).fetcher.get.apply(this)
  def getColumn25: Comparable[_ <: AnyRef] = context.columns(25).fetcher.get.apply(this)
  def getColumn26: Comparable[_ <: AnyRef] = context.columns(26).fetcher.get.apply(this)
  def getColumn27: Comparable[_ <: AnyRef] = context.columns(27).fetcher.get.apply(this)
  def getColumn28: Comparable[_ <: AnyRef] = context.columns(28).fetcher.get.apply(this)
  def getColumn29: Comparable[_ <: AnyRef] = context.columns(29).fetcher.get.apply(this)
  def getColumn30: Comparable[_ <: AnyRef] = context.columns(30).fetcher.get.apply(this)
  def getColumn31: Comparable[_ <: AnyRef] = context.columns(31).fetcher.get.apply(this)
  def getColumn32: Comparable[_ <: AnyRef] = context.columns(32).fetcher.get.apply(this)
  def getColumn33: Comparable[_ <: AnyRef] = context.columns(33).fetcher.get.apply(this)
  def getColumn34: Comparable[_ <: AnyRef] = context.columns(34).fetcher.get.apply(this)
  def getColumn35: Comparable[_ <: AnyRef] = context.columns(35).fetcher.get.apply(this)
  def getColumn36: Comparable[_ <: AnyRef] = context.columns(36).fetcher.get.apply(this)

}


trait HasKey extends Comparable[HasKey] {
  def key : String

  override def compareTo(o: HasKey): Int = key.compareTo(o.key)
}

class ExtrasBean( val context : ColumnContext,
                  @BeanProperty var json : JsonObject,
                  override val key : String) extends HasKey with HasJsonObject with JsonColumns {
  // Couldn't add beanproperty to the val reference as compiler complains we're not overriding getKey
  def getKey = key
}

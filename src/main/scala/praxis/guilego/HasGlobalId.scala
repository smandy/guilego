package praxis.guilego

// To aid with finder.
trait HasGlobalId extends Comparable[HasGlobalId] {
  def globalId: Int

  def compareTo(other: HasGlobalId): Int = Integer.compare(globalId, other.globalId)
}

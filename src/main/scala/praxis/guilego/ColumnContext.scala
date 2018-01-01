package argo.guilego

/* A little wormhole that we can use to serve as a placeholder for columns which can mutate from under us
*
* Columns are the json specific columns. The JsonColumns trait helps all the magic happen.
*
*
* */
class ColumnContext( var columns : Array[ScalaColumnInfo] = Array.empty[ScalaColumnInfo]) {
    var counter = 0

    def next(): String = {
      val ret = s"column$counter"
      counter += 1
      ret
    }

    def reset() {
      counter = 0
    }
}


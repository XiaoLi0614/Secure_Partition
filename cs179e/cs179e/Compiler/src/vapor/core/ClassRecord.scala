package vapor.core

import collection.mutable.HashMap
import lesani.compiler.typing.Symbol

class ClassRecord(parentRecOpt: Option[ClassRecord]) {

  val map = new HashMap[Symbol, Int]()
  var nextOffset = 0

  def getIndex(s: String): Option[Int] = {
    val symbol = Symbol.symbol(s)
    getIndex(symbol)
  }

  def getIndex(name: Symbol): Option[Int] = {
    map.get(name) match {
      case None =>
        parentGetIndex(name) match {
          case None =>
            None
          case s@Some(offset) =>
            s
        }
      case Some(offset) =>
        Some(parentSize + offset)
    }
  }
  private def parentGetIndex(name: Symbol): Option[Int] = {
    parentRecOpt match {
      case None =>
        None
      case Some(parentRec) =>
        parentRec.getIndex(name)
    }
  }

  def size: Int = parentSize + nextOffset

  private def parentSize: Int = parentRecOpt match {
    case None =>
      0
    case Some(parentRec) =>
      parentRec.size
  }

  def add(name: Symbol) {
    map.put(name, nextOffset);
    nextOffset += 1
  }

}
package vapor.core

import typechecking.types.Class
import collection.mutable.{ListBuffer, HashMap}
import lesani.compiler.typing.Symbol

class VTable(parentVTableOpt: Option[VTable]) {

  // seq is maintained to write the vtable for this class
  var seq: ListBuffer[Pair[Symbol, Class]] = parentVTableOpt match {
    case None => new ListBuffer[Pair[Symbol, Class]]()
    case Some(parentVTable) => parentVTable.seq.clone()
  }

  val map: HashMap[Symbol, Int] = parentVTableOpt match {
    case None => new HashMap[Symbol, Int]()
    // For each method gives the class whose method should be called!
    case Some(parentVTable) => parentVTable.map.clone()
  }

/*
  def getClass(name: String): Option[Class] = {
    getClass(Symbol.symbol(name))
  }
  def getClass(name: Symbol): Option[Class] = {
    map.get(name) match {
      case None => None
      case Some((i, clazz)) => Some(clazz)
    }
  }
*/

  def add(name: Symbol, clazz: Class) {
    map.get(name) match {
      case None => {
        map.put(name, seq.size);
        seq +=((name, clazz))
      }
      case Some(i) => {
        seq.update(i, (name, clazz))
        map.put(name, i)
      }
    }
  }

  def getIndex(name: Symbol) =
    map.get(name).get
  def getIndex(name: String) = {
    val symbol = Symbol.symbol(name)
    map.get(symbol).get
  }
}


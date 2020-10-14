package vapor.core

import typechecking.types.Class
import scala.collection.immutable.List
import lesani.collection.func.{Fun0, Fun}


object MakeClassTables {

  def apply(classes: List[Class]) {
    for (clazz <- classes) {

      // Get super class record
      val superClassOpt = clazz.superClassOpt
      val superClassRecordOpt = superClassOpt.apply(
        new Fun0[Option[ClassRecord]] {
          def apply() = None
        },
        new Fun[Class, Option[ClassRecord]] {
          def apply(superClass: Class) = Some(superClass.classRecord)
        }
      )
      // Prepare the class record
      val classRecord = new ClassRecord(superClassRecordOpt)
      val fields = clazz.fields
      val iter = fields.entrySet().iterator()
      while (iter.hasNext) {
        val symbolTypeEntry = iter.next()
        val symbol = symbolTypeEntry.getKey
        val Type = symbolTypeEntry.getValue
        classRecord.add(symbol)
      }
      clazz.classRecord = classRecord

      // Get super class VTable
      val superVTableOpt = superClassOpt.apply(
        new Fun0[Option[VTable]] {
          def apply() = None
        },
        new Fun[Class, Option[VTable]] {
          def apply(superClass: Class) = Some(superClass.vTable)
        }
      )
      // Prepare the VTable
      val vTable = new VTable(superVTableOpt)
      val methods = clazz.methods
      val iter2 = methods.entrySet().iterator()
      while (iter2.hasNext) {
        val symbolMethodEntry = iter2.next()
        val symbol = symbolMethodEntry.getKey
        vTable.add(symbol, clazz)
      }
      clazz.vTable = vTable

    }
  }

}



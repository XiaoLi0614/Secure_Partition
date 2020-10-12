package typechecking.direlicitor

import java.util

import lesani.alg.graph.toposort.{Graph, TopologicalSort}
import parsing.ast.tree.CompilationUnit
import lesani.collection.func.{Fun, Fun0}
import typechecking.exceptions.CyclicInheritanceExc
import typechecking.types.Class

import lesani.collection.option.Some
import lesani.collection.option.None
import lesani.collection.func.Fun
import lesani.collection.func.Fun0
//import scala.collection.immutable.List

import scala.collection.JavaConverters._

class ClassDirElicitor(compilationUnit: CompilationUnit) {

   def elicit: ClassDir = {
      var directory: ClassDir = SignatureReader.read(compilationUnit)
      TypeResolver.resolve(directory)


      val classes = directory.classMap.values()
      val adjacents = new util.HashMap[Class, util.Set[Class]]()

      val iter = classes.iterator();
      while(iter.hasNext()) {
         val theClass = iter.next();
         val superClassOption = theClass.superClassOpt
         val set = superClassOption.apply(
            new Fun0[util.HashSet[Class]] {
               def apply() = new util.HashSet[Class]()
            },
            new Fun[Class, util.HashSet[Class]] {
               def apply(superClass: Class) = {
                  val set = new util.HashSet[Class]()
                  set.add(superClass)
                  set
               }
            }
         )
         adjacents.put(theClass, set)

      }

      val graph = new Graph[Class](adjacents);


//      val classes = directory.classMap.values();
//      val graph = new Graph[Class]() {
//
//         def adjacents(theClass: Class) = {
//            val superClassOption = theClass.superClassOpt
//            superClassOption.apply(
//               new Fun0[Array[Class]] {
//                  def apply() = Array[Class]()
//               },
//               new Fun[Class, Array[Class]] {
//                  def apply(superClass: Class) = {
//                     Array(superClass)
//                  }
//               }
//            )
//         }
//
//         def nodes = classes.toArray(new Array(classes.size()))
//      }

      val sortedNodesOption = TopologicalSort.sort(graph)
      val sortedClasses: List[Class] = sortedNodesOption.apply(
         new Fun0[List[Class]] {
            def apply() = {
               new CyclicInheritanceExc
               null
            }
         },
         new Fun[util.List[Class], List[Class]] {
            def apply(l: util.List[Class]) = l.asScala.toList
         }
      )

//
      // We want the superclass, subclass order.
      directory.classes = sortedClasses.reverse
      directory
   }

}

object ClassDirElicitor {
   def apply(compilationUnit: CompilationUnit): ClassDir = {
      new ClassDirElicitor(compilationUnit).elicit
   }
}




package xtras

import collection.mutable.Set
import vaporm.{RegLoc, Interval}

object MyTest {
  def main(args: Array[String]) {
/*
    case class A(i: Int)
    val a = new A(1)
    val set = Set[A]()
    set += a
    println(set.contains(a))
*/

    val inter = new Interval(-1, 7, new RegLoc(0))
    val inter2 = new Interval(-1, 8, new RegLoc(1))

    val set = Set[Interval]()
    set += inter
    set += inter2
    println(set.contains(inter))
    println(set.contains(inter2))

  }
}
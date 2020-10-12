package vaporm


abstract class Location
case class RegLoc(num: Int) extends Location
case class StackLoc(offset: Int) extends Location
case class InStackLoc(offset: Int) extends Location


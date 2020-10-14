package vaporm


class Interval(var startLine: Int, var finishLine: Int, var location: Location) {
  override def toString = "[" + startLine + "-" + finishLine + "]"
}
// Making this a case class, brings a bug in the contains method of mutable.Set
// that is used in the linear scan as the type of fixedIntervals set.

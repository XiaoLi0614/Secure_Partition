package awkparse.exception

case class UnknownToken(lineNo: Int, columnNo: Int) extends Exception
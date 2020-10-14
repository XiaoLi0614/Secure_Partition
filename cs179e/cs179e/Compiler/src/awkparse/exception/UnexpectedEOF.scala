package awkparse.exception

case class UnexpectedEOF(lineNo: Int, columnNo: Int) extends Exception
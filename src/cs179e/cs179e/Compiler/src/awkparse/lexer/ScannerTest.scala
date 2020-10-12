package awkparse.lexer

import java.io.FileInputStream
import tokentype.EOF

/**
 * Created by IntelliJ IDEA.
 * User: Mohsen Lesani
 */

object ScannerTest extends App {
//  def main(args: Array[String]) {
//    var scanner: Scanner = new Scanner(new FileInputStream("/media/MOHSENHD/1.Works/3.Research/0.Topics/1.CDSC/2.Projects/0.Project/0.Project/src/jsrc/parser/lexer/test/Input.txt"))
    var scanner: Scanner = new Scanner(new FileInputStream("/media/MOHSENHD/1.Works/4.Course/1.CC/Project/Compiler/src/awkparse/lexer/test/Input2.txt"))
    var token: Token = scanner.goAhead()
    while (token.`type` ne EOF) {
      print(token)
      println("@" + token.lineNo + ":" + token.columnNo)
      token = scanner.goAhead()
    }
    println(token)
//  }

}



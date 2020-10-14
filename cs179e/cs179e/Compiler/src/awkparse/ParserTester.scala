package awkparse

object ParserTester {
  def test(fileName: String) {
    println("-----------------------------------")
    Parser.main(Array[String](fileName))
  }

  def main(args: Array[String]) {
    val path = "/media/MOHSENHD/1.Works/4.Course/1.CC/Project/Compiler/tests/awkparse/src/"
/*
    test(path + "Test1.awk")
    println("Should print:")
    println("1 $ 1 2 $ ++_ - + 3 $ _")
    println("Expression parsed successfully.")

    test(path + "Test2.awk")
    println("Should print:")
    println("1 $ $ _++ _++ 2 $ _")
    println("Expression parsed successfully.")

    test(path + "Test3.awk")
    println("Should print:")
    println("1 $ _++ _-- 2 $ _")
    println("Expression parsed successfully.")

    test(path + "Test4.awk")
    println("Should print:")
    println("1 $ _++ _-- 2 $ _")

    test(path + "Test5.awk")
    println("Should print:")
    println("Parse error in line 1.")
    println("(Token Type mismatch at 1:1. Found EOF. Expected one of Set(\"--\", \"$\", Num, \"++\", \"(\").)")

    test(path + "Test6.awk")
    println("Should print:")
    println("Parse error in line 3.")
    println("(Unknown token at 3:4.)")

    test(path + "Test7.awk")

    test(path + "Test8.awk")

    test(path + "Test9.awk")
    println("Should print:")
    println("1 $ ++_")
    println("Expression parsed successfully.")

    test(path + "Test10.awk")

    test(path + "Test11.awk")
*/
  }
}


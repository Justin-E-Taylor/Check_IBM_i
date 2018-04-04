import org.scalatest.FunSuite

class CheckMethodTest extends FunSuite {

  test("testRunCheck") {
    val check = new CheckMethod()
    check.RunCheck()
  }

  test("testSystemUp_Fail") {
    val check = new CheckMethod()
    assert(!check.SystemUp(List[String]("Failed", "Done")))
  }

  test("testSystemUp_Pass") {
    val check = new CheckMethod()
    assert(check.SystemUp(List[String]("Success", "Done")))
  }

}

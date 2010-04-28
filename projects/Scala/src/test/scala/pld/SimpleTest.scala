package pld


import org.junit.Assert._
import org.junit.Test
import org.junit.Before
import org.hamcrest.CoreMatchers._

class Adder {
  def add(a: Int, b: Int) = a + b
}

class SimpleTest {
  var adder: Adder = _

  @Before
  def init() {
    adder = new Adder()
  }

  @Test
  def verifySum() {
    assertThat(adder.add(2, 3), equalTo(5))
  }

}
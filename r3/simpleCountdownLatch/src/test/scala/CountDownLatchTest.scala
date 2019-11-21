package simpleCountdownLatch
import instrumentation.TestHelper._
import instrumentation.TestUtils._
import instrumentation._
import org.scalatest._

class CountDownLatchTest extends FunSuite{
  test("Should be zero after completing counting"){
    testManySchedules(3, sched =>{
      val cd = new SchedulableCountDownLatch(3, sched)
      val oops = for(i <- (0 until 3).toList) yield () => cd.countDown()
      def result(res: List[Any]) = (cd.count == 0, s"The count should be zero ${cd.count}")
      (oops, result)
    })
  }
  test("Should be not be zero if the number of threads are less than initCount"){
    testManySchedules(3, sched =>{
      val cd = new SchedulableCountDownLatch(4, sched)
      val oops = for(i <- (0 until 3).toList) yield () => cd.countDown()
      def result(res: List[Any]) = (cd.count != 0, s"The count should not be zero: ${cd.count}")
      (oops, result)
    })
  }
}

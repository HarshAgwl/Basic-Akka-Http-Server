package assignments

import akka.http.scaladsl.model.{StatusCodes}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{Matchers, WordSpec}

class HighLevelApiServerSpec extends WordSpec with Matchers with ScalatestRouteTest with SingleFieldJsonProtocol{

  import HighLevelApiServer._

  "Greet endpoint" should {
    "greet user with name" in {
      Get("/greet/John") ~> route ~> check {
        status shouldBe StatusCodes.OK
        entityAs[Greet] shouldBe Greet("Hello John")
      }
    }
  }

  "Health endpoint" should {
    "return OK message" in {
      Get("/health") ~> route ~> check {
        status shouldBe StatusCodes.OK
        entityAs[Status] shouldBe Status("OK")
      }
    }
  }

}

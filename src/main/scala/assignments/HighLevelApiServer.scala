package assignments

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.stream.ActorMaterializer
import spray.json._

case class Greet(message: String)

case class Status(status: String)

trait SingleFieldJsonProtocol extends DefaultJsonProtocol {
  implicit val greetFormat = jsonFormat1(Greet)
  implicit val statusFormat = jsonFormat1(Status)
}

object HighLevelApiServer extends App with SingleFieldJsonProtocol with SprayJsonSupport {

  implicit val system = ActorSystem("HighLevelApiServer")
  implicit val materializer = ActorMaterializer()

  import system.dispatcher
  import akka.http.scaladsl.server.Directives._


  val route =
    (path("greet" / Segment) & get) { nameOfUser: String =>
      complete(Greet(s"Hello $nameOfUser"))
    } ~
      (path("health") & get) {
        complete(Status("OK"))
      }


  Http().bindAndHandle(route, "localhost", 8080)
}

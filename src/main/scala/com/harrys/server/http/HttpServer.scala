package com.harrys.server.http

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import com.harrys.server.models.HttpServiceConfig
import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.ExecutionContext

/**
  * Created by chris on 5/15/17.
  */
final class HttpServer(config: HttpServiceConfig)
                      (implicit system: ActorSystem, mat: ActorMaterializer, ec: ExecutionContext)
  extends LazyLogging {

  val routes = Route.seal {
    get {
      path("api" / "v1" / "healthCheck") {
        complete("OK")
      }
    }
  }
}

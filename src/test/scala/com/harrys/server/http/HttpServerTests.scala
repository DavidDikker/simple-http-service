package com.harrys.server.http

import org.scalatest.{Matchers, WordSpec}
import akka.http.scaladsl.model._
import akka.http.scaladsl.testkit.ScalatestRouteTest

/**
  * Created by chris on 5/16/17.
  */
class HttpServerTests extends WordSpec with Matchers with ScalatestRouteTest {
  val config = TestConfig.basicConfigWithRandomPort()
  val server = new HttpServer(config)

  "The http service" must {
    "accept GET requests to the health check path" in {
      Get("/api/v1/healthCheck") ~> server.routes ~> check {
        responseAs[String] shouldEqual "OK"
      }
    }

    "reject requests to all unknown routes with a standard 404" in {
      Get("/home") ~> server.routes ~> check {
        status shouldEqual StatusCodes.NotFound
      }
    }
  }
}

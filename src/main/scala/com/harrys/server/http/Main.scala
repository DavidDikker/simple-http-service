package com.harrys.server.http

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import akka.util.Timeout
import com.harrys.server.models.HttpServiceConfig
import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.Await

/**
  * Created by chris on 5/15/17.
  */
object Main extends App with LazyLogging {

  val config = new HttpServiceConfig(ConfigFactory.load().resolve())

  val system = initializeHttpActorSystem(config)

  def initializeHttpActorSystem(config: HttpServiceConfig) : ActorSystem = {
    implicit val system = ActorSystem("simple-user-service-http", config.underlying)
    implicit val materializer = ActorMaterializer()
    implicit val timeout = Timeout(config.httpBindTimeout)

    import system.dispatcher

    val bindingFuture = Http().bindAndHandle(new HttpServer(config).routes,
      "0.0.0.0",
      config.httpListenPort)

    val httpBinding = try {
      Await.result[Http.ServerBinding](bindingFuture, config.httpBindTimeout)
    } catch {
      case e: Exception =>
        logger.error(s"Failed to bind to port ${ config.httpListenPort }", e)
        system.terminate()
        throw e
    }
    logger.info(s"HTTP server started successfully with binding: ${ httpBinding }")

    system
  }
}

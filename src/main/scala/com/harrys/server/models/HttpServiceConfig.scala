package com.harrys.server.models

import java.util.concurrent.TimeUnit._

import com.typesafe.config.Config

import scala.concurrent.duration.FiniteDuration

/**
  * Created by chris on 5/15/17.
  */
final class HttpServiceConfig(config: Config) {
  val basicAuthUsername = config.getString("basic-auth.username")
  val basicAuthPassword = config.getString("basic-auth.password")

  val httpBindTimeout = FiniteDuration(config.getDuration("akka.http.server.bind-timeout").toMillis, MILLISECONDS)
  val httpListenPort    = config.getInt("http.port")

  def underlying(): Config = config
}

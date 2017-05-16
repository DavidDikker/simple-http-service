package com.harrys.server.http

import java.net.ServerSocket

import com.harrys.server.models.HttpServiceConfig
import com.typesafe.config.{Config, ConfigFactory, ConfigValueFactory}

/**
  * Created by chris on 5/16/17.
  */
object TestConfig {

  def basicConfigWithRandomPort(): HttpServiceConfig = {
    val port = findRandomPort()
    val base = portConfig(port).withFallback(baseConfig()).withFallback(secretConfig(port)).resolve()
    new HttpServiceConfig(base)
  }

  private def portConfig(port: Int): Config = {
    ConfigFactory.empty().withValue("http.listen.port", ConfigValueFactory.fromAnyRef(port.asInstanceOf[java.lang.Integer]))
  }

  private def secretConfig(port: Int): Config = {
    ConfigFactory.empty()
  }

  private def baseConfig(): Config = {
    ConfigFactory.load("test.conf").withFallback(ConfigFactory.load())
  }

  private def findRandomPort(): Int = {
    val socket = new ServerSocket(0)
    try {
      socket.getLocalPort
    } finally socket.close()
  }
}

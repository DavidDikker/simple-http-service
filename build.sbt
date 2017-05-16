
name := "simple-http-server"

version := "0.1.0"

scalaVersion := "2.11.8"

dockerExposedPorts := Seq(8080)

lazy val IntegrationTest = config("it") extend(Test)
lazy val root = (project in file("."))
  .enablePlugins(JavaAppPackaging)
  .configs(IntegrationTest)
  .settings(Defaults.itSettings: _*)


libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % "1.1.3",
  "com.typesafe.akka" %% "akka-http" % "10.0.6",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.0.6",
  "com.typesafe" % "config" % "1.3.1",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0",
  "org.scalactic" %% "scalactic" % "3.0.1",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "com.typesafe.akka" %% "akka-testkit" % "2.4.11" % Test,
  "com.typesafe.akka" %% "akka-http-testkit" % "2.4.11" % Test,
  "org.scalamock" %% "scalamock-scalatest-support" % "3.5.0" % Test,
  "org.slf4j" % "slf4j-api" % "1.7.21"
)
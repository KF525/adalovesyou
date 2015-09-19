packageArchetype.java_application

name := "twibot"

version := "1.0"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.11" % "2.1.7" % "test",
  "org.twitter4j" % "twitter4j-core" % "4.0.3",
  "org.twitter4j" % "twitter4j-stream" % "4.0.3",
  "com.typesafe"  % "config"          % "1.3.0",
  "com.typesafe.slick" %% "slick" % "3.0.3",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "org.postgresql" % "postgresql" % "9.4-1203-jdbc42"
)

libraryDependencies ++= Seq(
  "com.h2database" % "h2" % "1.3.174"
)

Seq(flywaySettings: _*)

flywayUrl := scala.util.Properties.envOrElse("DATABASE_URL", "jdbc:postgresql://localhost:5432/twibot")

flywayUser := "katefulton"
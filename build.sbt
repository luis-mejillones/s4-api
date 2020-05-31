name := """s4-api"""
organization := "com.s4api"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.0"

/**
 * Dependencies
 */
libraryDependencies ++= Seq (
  guice,
  javaWs,
  "org.mongodb" % "mongodb-driver" % "3.11.0",
  "com.fasterxml.jackson.core" % "jackson-core" % "2.9.9",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.9.9",
  "com.fasterxml.jackson.core" % "jackson-annotations" % "2.9.9",
  "org.apache.commons" % "commons-lang3" % "3.9"
).map(_.force())

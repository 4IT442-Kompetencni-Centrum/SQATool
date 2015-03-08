import play.PlayJava

name := """sqatool"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  javaJpa,
  "org.hibernate" % "hibernate-entitymanager" % "4.2.5.Final",
  "postgresql" % "postgresql" % "9.1-901-1.jdbc4",
  cache,
  javaWs
)

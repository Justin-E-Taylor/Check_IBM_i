name := "Check_IBM_i"

version := "0.1"

scalaVersion := "2.12.5"


// https://mvnrepository.com/artifact/org.apache.commons/commons-email
libraryDependencies += "org.apache.commons" % "commons-email" % "1.5"

// https://github.com/lightbend/config
libraryDependencies += "com.typesafe" % "config" % "1.3.2"

// ScalaTest
libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"
